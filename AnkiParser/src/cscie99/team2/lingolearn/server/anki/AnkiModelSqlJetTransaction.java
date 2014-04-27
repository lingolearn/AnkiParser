package cscie99.team2.lingolearn.server.anki;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import cscie99.team2.lingolearn.server.anki.models.CardModel;

public class AnkiModelSqlJetTransaction implements ISqlJetTransaction {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(AnkiModelSqlJetTransaction.class);
	private AnkiFile ankiFile;

	
	public AnkiModelSqlJetTransaction( AnkiFile ankiFile ){
		this.ankiFile = ankiFile;
	}
	
	public ISqlJetTransaction run(SqlJetDb dbloc)
	throws SqlJetException
	{
		
		ISqlJetCursor cursor = dbloc.getTable("col").open();
		try {
			cursor.first();
			while (!cursor.eof()) {
				String fldsString = cursor.getString("models");
				//String[] flds = fldsString.split("");
				HashMap<String, CardModel> models = AnkiFile.getModels(fldsString);
				this.ankiFile.setModels( models );
				Set<String> keys = models.keySet();
				Iterator<String> keyItr = keys.iterator();
				while( keyItr.hasNext() ){
					String modelKey = keyItr.next();
					CardModel model = models.get(modelKey);
					logger.info("Card Model id: " + model.getId());
				}
				logger.info("Parsed Card Field String: \n" + fldsString);
				cursor.next();
			}
		}catch(IOException ioe){
			throw new SqlJetException(
					"An IO Read error occurred durring the SqlLite transaction.");
		}
		finally {
			cursor.close();
			
		}
		return null;

	}

}
