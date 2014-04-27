package cscie99.team2.lingolearn.server.anki;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import cscie99.team2.lingolearn.server.anki.error.AnkiException;
import cscie99.team2.lingolearn.server.anki.models.CardField;
import cscie99.team2.lingolearn.server.anki.models.CardModel;
import cscie99.team2.lingolearn.shared.Card;
import cscie99.team2.lingolearn.shared.Deck;

public class AnkiFile {

	private File sqliteFile;
	private FileInputStream zipFileStream;
	private SqlJetDb db;
	private String tmpFolder = "TMP";
	private String ankiFilename = "";
	private HashMap<String, CardModel> models;
	private int totalCards = 0;
	private int totalDeckModels = 0;
	private HashMap<String, Deck> parsedDecks;
	private AnkiModelSqlJetTransaction modelTransaction;
	private AnkiCardSqlJetTransaction cardTransaction;
	
	public AnkiFile(String fileName) 
	throws AnkiException
	{
		
		try{
			this.modelTransaction = new AnkiModelSqlJetTransaction(this);
			this.cardTransaction = new AnkiCardSqlJetTransaction(this);
			this.zipFileStream = new FileInputStream(fileName);
			this.parsedDecks = new HashMap<String, Deck>();
			this.ankiFilename = fileName;
			
			this.importData();
		}catch( SqlJetException sje ){
			throw new AnkiException(
					"An error occured parsing the Anki SqlLite databse", sje);
		}catch(FileNotFoundException fnf ){
			throw new AnkiException(
					"The specified Anki file was not found: " + fileName, fnf);
		}catch( IOException ioe ){
			throw new AnkiException(
					"An error occured parsing the Anki file.", ioe);
		}
	}
	
	/*
	 * Helper method to import the Card and model data contained
	 * in the AnkiDeck
	 */
	private void importData() throws SqlJetException, IOException {
		
		ZipInputStream zis = new ZipInputStream(zipFileStream);
    	
	    ZipEntry ze = zis.getNextEntry();	 
	    while (ze!=null) {
	    	if (ze.getName().contains(".anki2")) {
	    		this.sqliteFile = new File(this.tmpFolder + File.separator + ze.getName());
	    
	    		//Create parent folder
	    		new File(this.sqliteFile.getParent()).mkdirs();
	    		 
	            FileOutputStream fos = new FileOutputStream(this.sqliteFile);   
	            byte[] buffer = new byte[1024];
	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	            	fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	    	}

            ze = zis.getNextEntry();
	    }
	    zis.closeEntry();
    	zis.close();
	    
		SqlJetDb db = SqlJetDb.open(this.sqliteFile, false);
        
		db.runReadTransaction( this.modelTransaction );
		db.runReadTransaction( this.cardTransaction );
		db.close();
		
	}
	
	/**
	 * Add a Card to the collection of parsed cards and decks
	 * @param deckName the name of the deck as parsed from the anki file.  If 
	 * no "name" exists this will be the model id.
	 * @param card the card object as parsed from the anki file.
	 */
	public void addCard( String deckName, Card card ){
		Deck deck = parsedDecks.get(deckName);
		if( deck == null ){
			deck = new Deck();
			totalDeckModels++;
			deck.setName( getNextDeckName() );
		}
		
		deck.add(card);
		
		parsedDecks.put( deckName, deck );
		totalCards++;
	
	}
	
	/**
	 * Parse a Card object based on the fields and model read from the 
	 * Anki file.
	 * @param cardFields an array of fields read in from the Anki file that represent
	 * an individual card.
	 * @param modelId the model id for this card as read in from the anki file.
	 * @return a Card object parsed from the specified fields and model.
	 */
	public Card buildCard( String cardFields[], String modelId ){
		
		CardModel model = models.get(modelId);
		
		//TODO more robust error handling here
		if( model == null )
			return null;
		
		Card card = new Card();
		CardField modelFields[] = model.getFlds();
		for( int i = 0; i < modelFields.length; i++ ){
			CardField modelField = modelFields[i];
			AnkiFieldTypes fieldType = getModelFieldType(modelField);
			String cardFieldValue = cardFields[i];
			switch( fieldType ){
				
				case Front:
					// Determine if value is Hiragana or other alphabet
					card.setHiragana( cardFieldValue );
					break;
				
				case Back:
					String cardBack = cardFieldValue;
					// Use RegEx to remove chars between [ ].  Some anki files
					// this notation to indicate embedded media seperate from
					// the translation.
					cardBack = cardBack.replaceAll("(.*)(\\[.*\\])", "$1");
					card.setTranslation( cardBack );
					break;
				
				case Image:
					//TODO: setImage Handler!
					break;
					
				case Sound:
					//TODO: set Sound Handler!
					break;
				default:
					continue;
			
			}
		}
		
		return card;
		
	}
	
	private String getNextDeckName(){
		File ankiFile = new File(this.ankiFilename);
		String filename = ankiFile.getName();
		//String fileTokens[]
		String fileTokens[] = filename.split("\\.(?=[^\\.]+$)");
		if( fileTokens.length < 1 )
			return filename;
		
		String basename = fileTokens[0];
		
		
		String deckName = basename;
		if( totalDeckModels > 1 )
			deckName += totalDeckModels;
		
		return deckName;
	}
	
	/*
	 * Return the AnkiFieldTypes enum represented in the CardField
	 * passed.
	 */
	private AnkiFieldTypes getModelFieldType( CardField field ){
		AnkiFieldTypes types[] = AnkiFieldTypes.values();
		for( int i = 0; i < types.length; i++ ){
			AnkiFieldTypes type = types[i];
			String fieldName = field.getName();
			if( type.toString().equalsIgnoreCase(fieldName))
				return type;
		}
		
		return null;
	}
	
	/**
	 * Return a Map of the Card Model structures that are represented
	 * in the Anki file.  This is performed by parsing the JSON that
	 * is contained in the models column of the cols table in the
	 * SqlLite databse.
	 * @param modelMap the value of the models column in the cols table of the AnkiFile's
	 * SqlLite database.
	 * @return a Map collection that keys the String modelId to the Model datastructure
	 * itself.
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static HashMap<String, CardModel> getModels(String modelMap)
	throws JsonMappingException, JsonParseException, IOException
	{
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, CardModel> models = mapper.readValue( 
				modelMap, new TypeReference<HashMap<String, CardModel>>(){});
				
		Set<String> keys = models.keySet();
		for( String mid : keys ){
			CardModel model = models.get(mid);
			//System.out.println(model.getId());
		}
		
		return models;
	}
	
	
	
	public HashMap<String, Deck> getParsedDecks() {
		return parsedDecks;
	}

	public HashMap<String, CardModel> getModels() {
		return models;
	}

	public void setModels(HashMap<String, CardModel> models) {
		this.models = models;
	}

	public int getTotalCards() {
		return totalCards;
	}

	public void setTotalCards(int totalCards) {
		this.totalCards = totalCards;
	}

	public int getTotalDeckModels() {
		return totalDeckModels;
	}

	public void setTotalDeckModels(int totalDeckModels) {
		this.totalDeckModels = totalDeckModels;
	}
	
	
}
