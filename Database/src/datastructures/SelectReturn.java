package datastructures;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SelectReturn
{

	private Connection c;
	private List<ArrayList<String>> values;
	
	public SelectReturn(Connection c, List<ArrayList<String>> values) {
		this.c = c;
		this.values = values;
	}
	
	public Connection getConnection() {
		return this.c;
	}
	
	public List<ArrayList<String>> getValues() {
		return this.values;
	}
	
	public void setConnection(Connection c) {
		this.c = c;
	}
	
	public void setValues(List<ArrayList<String>> values) {
		this.values = values;
	}
	
}
