package querybuilders;

import java.util.ArrayList;

import exceptions.SQLLengthException;

public class UpdateBuilder extends AbstractBuilder {
	
	private ArrayList<String> columns;
	private ArrayList<String> values;
	private ArrayList<String> conditions;

	/**
	* Constructor for creating UPDATE query for DB
	* @param tablename - Name of table to update
	* @param columns - Columns to update as <code>ArrayList</code> of <code>String</code> objects
	* @param values - New values to use as <code>ArrayList</code> of <code>String</code> objects
	* @param conditions - Conditions to filter records to update as <code>ArrayList</code> of <code>String</code> objects
	* @throws SQLLengthException If tablename is invalid, length of values and columns lists are not equal or if condition(s) are invalid
	**/
	public UpdateBuilder(String tablename, ArrayList<String> columns,
			ArrayList<String> values, ArrayList<String> conditions) throws SQLLengthException {
		
		super(tablename);
		
		if (columns.size() != values.size()) {
			throw new SQLLengthException();
		}
		
		this.columns = columns;
		this.values = values;
		this.conditions = conditions;
		
	}

	@Override
	protected void build() {
		String statement = "UPDATE " + this.getTablename() + " SET ";
		
		for (int i=0;i < this.columns.size();i++) {
			statement += columns.get(i) + " = " + this.values.get(i);
			
			if (i != this.columns.size()-1) {
				statement += ", ";
			}
		}
		
		if (this.conditions.size() != 0) {
			statement += " WHERE ";
			
			for (int i=0;i < this.conditions.size();i++) {
				statement += this.conditions.get(i);
				
				if (i != this.conditions.size()-1) {
					statement += " AND ";
				}
			}
		}
		
		statement += ";";
		
		this.setStatement(statement);
		
	}

}
