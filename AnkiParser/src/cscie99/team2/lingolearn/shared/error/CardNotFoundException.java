/**
 * CSCIE99 TEAM 2
 */
package cscie99.team2.lingolearn.shared.error;

/**
 * This exception should be thrown when a card cannot be found.
 */
@SuppressWarnings("serial")
public class CardNotFoundException extends Exception {
	
	private String msg;			// Message from called method
	private String searchAttr;	// Attribute that was used as a search critera
	private String searchParam;	// Search parameter
	private Throwable cause;	// Chained exception
	
	/**
	 * Constructor, note the exception reason.
	 */
	public CardNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * @param msg Message from called method 
	 * @param searchAttr Attribute that was used as a search critera
	 * @param searchParam Search parameter
	 */
	public CardNotFoundException(String msg, String searchAttr, String searchParam) {
		this.msg = msg;
		this.searchAttr = searchAttr;
		this.searchParam = searchParam;
	}
	
	/**
	 * 
	 * @param msg Message from called method 
	 * @param searchAttr Attribute that was used as a search critera
	 * @param searchParam Search parameter
	 * @param cause Chained exception
	 */
	public CardNotFoundException(String msg, String searchAttr, String searchParam, Throwable cause) {
		this.msg = msg;
		this.searchAttr = searchAttr;
		this.searchParam = searchParam;
		this.cause = cause;
	}
	
	/**
	 * 
	 * @param msg Message from called method 
	 * @param cause Chained exception
	 */
	public CardNotFoundException(String msg, Throwable cause) {
		super("CardNotFoundException occurred: "  + cause);
		this.msg = msg;
		this.cause = cause;
	}
	// Getters

	public String getMsg() {
		return msg;
	}

	public String getSearchAttr() {
		return searchAttr;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public Throwable getCause() {
		return cause;
	}
}
