package querybuilders;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractBuilder {
	
	private String tablename;
	
	private ArrayList<String> tablenames;
	
	private String statement;
	
	/**
	 * Constructor used when only 1 table is specified as part of the query
	 * @param tablename
	 */
	public AbstractBuilder(String tablename) {
		this.tablename = tablename;
	}
	
	/**
	* Constructor used when 2 tables are specified as part of the query
	* @param tablename1 - First table
	* @param tablename2 - Second table
	**/
	public AbstractBuilder(String tablename1, String tablename2) {
		this.tablenames = new ArrayList<String>(Arrays.asList(tablename1,tablename2));
	}

	/**
	 * Unpacks array of strings into single string with comma separated parts
	 * @param parts - <code>ArrayList</code> of <code>String</code> objects showing parts of statement
	 * @return <code>String</code> of comma separated values for query
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
	
	/**
	* Method for building SQL statement
	**/
	protected abstract void build();
	
	/**
	 * Getter method for tablename of query
	 * @return <code>String</code> will be null if 2 tables are involved in statement
	 */
	protected String getTablename() {
		return this.tablename;
	}
	
	/**
	 * Setter method for statement <code>String</code> used to query
	 * @param s - <code>String</code> to use
	 */
	protected void setStatement(String s) {
		this.statement = s;
	}
	
	/**
	 * Getter method for query statement
	 * @return statement <code>String</code>
	 */
	public String getStatement() {
		return this.statement;
	}
	
	/**
	* Getter method for tablenames of query (when 2 tables are involved)
	* @return <code>ArrayList</code> of tablenames used in query
	**/
	protected ArrayList<String> getTablenames() {
		return tablenames;
	}
}
