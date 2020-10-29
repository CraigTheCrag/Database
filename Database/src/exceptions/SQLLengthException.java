package exceptions;

import java.sql.SQLException;

public class SQLLengthException extends SQLException {
	
	// DEFAULT ERROR MESSAGE:
	private static final String DEFAULT_ERROR = "Invalid Number of Values";
	
	/**
	* Default constructor - calls parent constructor with DEFAULT_ERROR
	**/
	public SQLLengthException() {
		super(DEFAULT_ERROR);
	}
	
	/**
	* Constructor calls parent constructor with specified message
	* @param s - Message to use
	**/
	public SQLLengthException(String s) {
		super(s);
	}
	
}
