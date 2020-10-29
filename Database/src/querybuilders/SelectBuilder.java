package querybuilders;

import java.util.ArrayList;

public class SelectBuilder extends AbstractBuilder {
	
	private String columns;
	
	private ArrayList<String> conditions;

	/**
	* Constructor for building SELECT query statements
	* @param tablename - Name of table to select data from
	* @param columns - <code>ArrayList</code> of <code>String</code> objects of names of columns to use
	* @param conditions - <code>ArrayList</code> of <code>String</code> objects of conditions to use
	**/
	public SelectBuilder(String tablename, ArrayList<String> columns, ArrayList<String> conditions) {
		super(tablename);
		
		if (columns.size() != 0) {
			this.columns = this.unpack(columns).trim();
		} else {
			this.columns = "*";
		}

		this.conditions = conditions;
		
		this.build();
	}
	
	/**
	* Constructor for building SELECT query statements when all columns want selecting
	**/
	public SelectBuilder(String tablename) {
		super(tablename);
		
		this.columns = "*";
		
		this.build();
	}
	
	@Override
	protected void build() {
		String statement = "SELECT " + this.columns + " FROM " + this.getTablename();
		
		if (this.conditions.size() != 0) {
			statement += " WHERE ";
			
			for (int i=0;i < this.conditions.size();i++) {
				statement += this.conditions.get(i);
				
				if (i != this.conditions.size()-1) {
					statement += " AND ";
				}
			}
			
			statement += ";";
		}
		
		this.setStatement(statement);
		
	}

}
