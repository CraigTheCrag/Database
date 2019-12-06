package querybuilders;

import java.util.ArrayList;

import exceptions.SQLLengthException;

public class TableBuilder extends AbstractBuilder {
	
	ArrayList<String> columns;
	ArrayList<String> types;
	
	ArrayList<Boolean> nulls;
	
	String columnString;
	String typesString;

	/**
	 * Constructor for sql table builder
	 * @param tablename
	 * @param columns
	 * @param types
	 * @param nulls
	 */
	public TableBuilder(String tablename, ArrayList<String> columns,
			ArrayList<String> types, ArrayList<Boolean> nulls) throws SQLLengthException {
		
		super(tablename);
		
		if (columns.size() == types.size() && types.size() == nulls.size()) {
			this.columns = columns;
			this.types = types;
			this.nulls = nulls;
		} else {
			throw new SQLLengthException();
		}
		
		this.build();
	}

	@Override
	protected void build() {
		String statement = "CREATE TABLE " + this.getTablename() + " ";
		statement += "(" + this.columns.get(0) + " " + this.types.get(0) + " PRIMARY KEY NOT NULL, ";
		
		for (int i=1;i < this.columns.size();i++) {
			statement += this.columns.get(i) + " " + this.types.get(i);
			if (!this.nulls.get(i)) {
				statement += " NOT NULL";
			}
			statement += ", ";
		}
		
		statement = statement.substring(0, statement.length()-2);
		statement +=  ")";
		
		this.setStatement(statement);
	}

}
