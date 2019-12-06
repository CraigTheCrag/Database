package exceptions;

import java.sql.SQLException;

public class SQLLengthException extends SQLException {

	private static final String DEFAULT_ERROR = "Invalid Number of Values";
	
	
	public SQLLengthException() {
		super(DEFAULT_ERROR);
	}
	
	public SQLLengthException(String s) {
		super(s);
	}
	
}
