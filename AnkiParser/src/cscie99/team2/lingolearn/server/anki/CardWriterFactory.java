package cscie99.team2.lingolearn.server.anki;

public class CardWriterFactory {
	
	public static CardWriter getCardWriter(){
		return new SimpleCardCsvWriter();
			
	
	}
}
