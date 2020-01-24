package querybuilders;

public class TableDestroyer extends AbstractBuilder {

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
