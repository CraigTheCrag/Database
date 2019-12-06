package launcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import querybuilders.InsertBuilder;
import querybuilders.SelectBuilder;
import querybuilders.TableBuilder;

public class DatabaseMain {
	
	private static final String PATH = "/home/craig/eclipse-workspace/Database/first.db";

	public static void main(String[] args) {
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + PATH);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Opened database successfully");
		
		String tablename = "COMPANY";
		ArrayList<String> columns = new ArrayList<String>(Arrays.asList("ID", "NAME", "AGE", "ADDRESS", "SALARY"));
		ArrayList<String> types = new ArrayList<String>(Arrays.asList("INT", "TEXT", "INT", "CHAR(50)", "REAL"));
		ArrayList<Boolean> nulls = new ArrayList<Boolean>(Arrays.asList(false, false, false, true, false));
		
		ArrayList<String> values = new ArrayList<String>(Arrays.asList("0", "'Craig'", "19", "'22 The Russetts'", "200000.00"));
		
		try {
			//c = createTable(c, tablename, columns, types, nulls);
			c = insertRecord(c, tablename, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Table created successfully!");
		
	}
	
	private static Connection createTable(Connection c, String tablename, 
			ArrayList<String> columns, ArrayList<String> types, 
			ArrayList<Boolean> nulls) throws SQLException {
		
		TableBuilder tb = new TableBuilder(tablename, columns, types, nulls);
		
		System.out.println(tb.getStatement());
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(tb.getStatement());
		
		return c;
	}
	
	private static Connection insertRecord(Connection c, String tablename,
			ArrayList<String> values) throws SQLException {
		
		InsertBuilder ib = new InsertBuilder(tablename, values);
		
		System.out.println(ib.getStatement());
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(ib.getStatement());
		
		return c;
		
	}
	
	private static Connection selectRecords(Connection c, String tablename,
			ArrayList<String> columns, ArrayList<String> conditions) throws SQLException {
		
		SelectBuilder sb = new SelectBuilder(tablename, columns, conditions);
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(sb.getStatement());
		
		return c;
		
	}
	
}
