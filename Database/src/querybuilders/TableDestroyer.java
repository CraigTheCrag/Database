package querybuilders;

public class TableDestroyer extends AbstractBuilder {

	/**
	* Constructor for destroying (DROPping)specific table
	* @param tablename - Name of table to destroy (DROP)
	**/
	public TableDestroyer(String tablename)
	{
		super(tablename);
	}

	@Override
	protected void build()
	{
		String statement = "DROP TABLE " + this.getTablename();
		this.setStatement(statement);
		
	}

}
