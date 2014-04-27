package cscie99.team2.lingolearn.server.anki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import cscie99.team2.lingolearn.shared.Card;



/**
 * This class Transaction class parses the Anki SqlLite db file for
 * the sole purpose of parsing Card entities.  The transaction should
 * populate the AnkiFile object with its appropriate collection of
 * cards / decks, or it should record the appropriate exception.
 * 
 * Client Classes of this transaction (or the AnkiFile) should glean
 * only the Cards OR an error message, not both.
 * @author jrabe
 *
 */
public class AnkiCardSqlJetTransaction implements ISqlJetTransaction {

	private static final Logger logger = 
			LoggerFactory.getLogger(AnkiCardSqlJetTransaction.class);
	
	private AnkiFile ankiFile;
	
	public AnkiCardSqlJetTransaction( AnkiFile ankiFile ){
		this.ankiFile = ankiFile;
	}
	
	public ISqlJetTransaction run(SqlJetDb dbloc)
	throws SqlJetException
	{
		ISqlJetCursor cursor = dbloc.getTable("notes").open();
		try {
			cursor.first();
			while (cursor.next()) {
				String modelId = cursor.getString("mid");
				String fldsString = cursor.getString("flds");
				String[] flds = fldsString.split("");
				Card parsedCard = this.ankiFile.buildCard(flds, modelId);
				logger.info("Card Parsed: " + flds[0]);
				
				ankiFile.addCard(modelId, parsedCard);
			}
		} finally {
			cursor.close();
		}
		return null;
	}
}
