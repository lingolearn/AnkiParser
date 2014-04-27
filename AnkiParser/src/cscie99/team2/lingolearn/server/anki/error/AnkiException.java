package cscie99.team2.lingolearn.server.anki.error;

public class AnkiException extends Exception {

	public Exception caught;
	
	public AnkiException( String message ){
		this(message, null);
	}
	
	public AnkiException( String message, Exception caught ){
		super(message);
		this.caught = caught;
	}
}
