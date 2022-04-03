package server;

/**
 * Represents an error in the database.
 *
 */
public class DBException extends Exception {
	
	private String message;
	
	public DBException(String message) throws DBException{
		super(message);
		this.message = message;
	}

	public DBException(String message, Throwable t) throws DBException{
		super(message, t);
		this.message = message;
	}
	
	public String toString(){
		return message;
	}
}

