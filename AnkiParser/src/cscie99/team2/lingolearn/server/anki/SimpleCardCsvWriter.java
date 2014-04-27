package cscie99.team2.lingolearn.server.anki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cscie99.team2.lingolearn.shared.Card;
import cscie99.team2.lingolearn.shared.Deck;

public class SimpleCardCsvWriter implements CardWriter {

	private static final Logger logger = LoggerFactory.getLogger(SimpleCardCsvWriter.class);
	
	public static final String DELIM = "\t";
	public static final String LF= "\n";
	
	public SimpleCardCsvWriter(){
		
	}
	
	public String writeCard( Card card, Deck deck ){
		
		if( !cardIsValid( card ) ){
			logger.warn("Invalid card found: " + card.getId());
			return "";
		}
			
		
		StringBuffer buf = new StringBuffer();
		buf.append( card.getKanji() );
		buf.append(DELIM);
		buf.append( card.getHiragana() );
		buf.append(DELIM);
		buf.append( card.getKatakana() );
		buf.append(DELIM);
		buf.append( card.getNativeLanguage().toString() );
		buf.append(DELIM);
		buf.append( card.getTranslation() );
		buf.append(DELIM);
		buf.append( deck.getName() );
		buf.append( LF );
		
		return buf.toString();
	}
	
	public String writeMedia( Deck deck ){
		return "";
	}
	
	public boolean cardIsValid(Card card){
		if( card.getHiragana().isEmpty() && 
				card.getKanji().isEmpty() && 
				card.getKatakana().isEmpty() ){
			return false;
		}
		
		if( card.getNativeLanguage() == null )
			return false;
		
		if( card.getTranslation().isEmpty() )
			return false;
		
		return true;
	}
}
