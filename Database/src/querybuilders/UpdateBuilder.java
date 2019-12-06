package querybuilders;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateBuilder extends AbstractBuilder {
	
	private ArrayList<String> columns;
	private ArrayList<String> values;
	private ArrayList<String> conditions;

	public UpdateBuilder(String tablename, ArrayList<String> columns,
			ArrayList<String> values, ArrayList<String> conditions) throws SQLException {
		
		super(tablename);
		
		if (columns.size() != values.size()) {
			throw new SQLException("Number of columns and values not equal");
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
		
		statement += ");";
		
		this.setStatement(statement);
		
	}

}
