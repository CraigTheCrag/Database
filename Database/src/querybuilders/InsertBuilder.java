package querybuilders;

import java.util.ArrayList;

public class InsertBuilder extends AbstractBuilder {
	
	private ArrayList<String> values;
	
	private String unpackedString;
	
	/**
	 * Constructor for insert SQL statement
	 * @param tablename
	 * @param values
	 */
	public InsertBuilder(String tablename, ArrayList<String> values) {
		
		super(tablename);
		
		this.values = values;
		
		this.unpackedString = this.unpack(this.values);
		
		this.build();
	}

	@Override
	protected void build() {
		String newStatement = "INSERT INTO " + this.getTablename() + " VALUES(";
		newStatement += this.unpackedString + ");";
		
		this.setStatement(newStatement);
	}
	
}
