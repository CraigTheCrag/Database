package querybuilders;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractBuilder {
	
	private String tablename;
	
	private ArrayList<String> tablenames;
	
	private String statement;
	
	/**
	 * Constructor for abstract SQL statement builder
	 * @param tablename
	 */
	public AbstractBuilder(String tablename) {
		this.tablename = tablename;
	}
	
	public AbstractBuilder(String tablename1, String tablename2) {
		this.tablenames = new ArrayList<String>(Arrays.asList(tablename1,tablename2));
	}

	/**
	 * Unpacks array of strings into single string
	 * @param parts
	 * @return
	 */
	protected String unpack(ArrayList<String> parts) {
		String newString = "";
		
		for (int i=0;i < parts.size();i++) {
			newString += parts.get(i);
			
			if (i != parts.size()-1) {
				newString += ",";
			}
			newString += " ";
		}
		
		return newString;
	}
	
	protected abstract void build();
	
	/**
	 * Returns table name of current table
	 * @return
	 */
	protected String getTablename() {
		return this.tablename;
	}
	
	/**
	 * Sets built SQL statement
	 * @param s
	 */
	protected void setStatement(String s) {
		this.statement = s;
	}
	
	/**
	 * Returns build SQL statement
	 * @return
	 */
	public String getStatement() {
		return this.statement;
	}
	
	protected ArrayList<String> getTablenames() {
		return tablenames;
	}
}
