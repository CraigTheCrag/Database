package datastructures;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SelectReturn
{

	private Connection c;
	private List<ArrayList<String>> values;
	
	/**
	* Creates return object from SELECT SQL statement
	* @param c - Connection to DB
	* @param values - Values returned from SELECT statement
	**/
	public SelectReturn(Connection c, List<ArrayList<String>> values) {
		this.c = c;
		this.values = values;
	}
	
	/**
	* Getter method for DB connection
	* @return connection to DB
	**/
	public Connection getConnection() {
		return this.c;
	}
	
	/**
	* Getter method for SELECT values from DB
	* @return values from statement
	**/
	public List<ArrayList<String>> getValues() {
		return this.values;
	}
	
	/**
	* Setter method for DB connection
	* @param c - Connection to use
	**/
	public void setConnection(Connection c) {
		this.c = c;
	}
	
	/**
	* Setter method for SELECT values from DB
	* @param values - Values to use
	**/
	public void setValues(List<ArrayList<String>> values) {
		this.values = values;
	}
	
}
