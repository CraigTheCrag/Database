package querybuilders;

import java.util.ArrayList;

public class JoinBuilder extends AbstractBuilder {

	public static enum JoinType {INNER, LEFT, RIGHT, FULL, SELF};
	
	private JoinType selectedType;
	
	private String columns;
	private String column1;
	private String column2;
	
	/**
	* Constructor for join statement
	* @param tablename1 - First table to use in join query
	* @param tablename2 - Second table to use in join query
	* @param columns - <code>ArrayList</code> of columns to join
	* @param type - Type of join statement to used, specified with <code>JoinType enum</code>
	**/
	public JoinBuilder(String tablename1, String tablename2,
			ArrayList<String> columns, JoinType type) {
		
		super(tablename1, tablename2);
		
		if (columns.size() == 0) {
			this.columns = "*";
		} else {
			this.columns = this.unpack(columns).trim();
		}
		
		this.selectedType = type;
		
		this.build();
	}

	@Override
	protected void build() {
		String statement = "SELECT " + this.columns +
				" FROM " + this.getTablenames().get(0) + " " + selectedType.toString() +
				" JOIN " + this.getTablenames().get(1) + " ON " +
				this.getTablenames().get(0) + "." + this.column1 +
				" = " + this.getTablenames().get(1) + "." + this.column2 + ";";
		
		this.setStatement(statement);
		
	}

}
