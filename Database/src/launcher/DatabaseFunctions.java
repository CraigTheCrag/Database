package launcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datastructures.SelectReturn;
import querybuilders.InsertBuilder;
import querybuilders.SelectBuilder;
import querybuilders.TableBuilder;
import querybuilders.TableDestroyer;

public class DatabaseFunctions {
	
	/**
	* Creates <code>TableDestroyer</code> object to drop specified table
	* @param c - <code>Connection</code> to DB
	* @param tablename - Name of table to DROP
	* @return <code>Connection</code> to DB
	* @throws SQLException If tablename is invalid
	**/
	public static Connection dropTable(Connection c, String tablename) throws SQLException {
		TableDestroyer td = new TableDestroyer(tablename);
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(td.getStatement());
		
		return c;
	}
	
	/**
	* Creates <code>TableBuilder</code> object to create new table
	* @param c - <code>Connection</code> to DB
	* @param tablename - Name of table to create
	* @param columns - <code>ArrayList</code> of column names to use
	* @param types - <code>ArrayList</code> of (SQL) types to use, as <code>String</code> objects
	* @param nulls - <code>ArrayList</code> of <code>Boolean</code> objects specifying whether the corresponding column can be null or not (true = null, false = not null)
	* @return <code>Connection</code> to DB
	* @throws SQLException If tablename is invalid or lengths of other parameters are not matching
	**/
	public static Connection createTable(Connection c, String tablename, 
			ArrayList<String> columns, ArrayList<String> types, 
			ArrayList<Boolean> nulls) throws SQLException {
		
		TableBuilder tb = new TableBuilder(tablename, columns, types, nulls);
		
		System.out.println(tb.getStatement());
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(tb.getStatement());
		
		return c;
	}
	
	/**
	* Creates <code>InsertBuilder</code> object to insert values into table
	* @param c - <code>Connection</code> to DB
	* @param tablename - Name of table to insert values into
	* @param values - <code>ArrayList</code> of <code>String</code> objects of values to insert
	* @return <code>Connection</code> to DB
	* @throws SQLException If tablename is invalid, length of values list is not matching or if types of values inserting do not match table
	**/
	public static Connection insertRecord(Connection c, String tablename,
			ArrayList<String> values) throws SQLException {
		
		InsertBuilder ib = new InsertBuilder(tablename, values);
		
		System.out.println(ib.getStatement());
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(ib.getStatement());
		
		return c;
		
	}

	/**
	* Creates <code>SelectBuilder</code> object to select values from table
	* @param c - <code>Connection</code> to DB
	* @param tablename - Name of table to select values from
	* @param columns - <code>ArrayList</code> of <code>String</code> objects of columns to select data from
	* @param conditions - <code>ArrayList</code> of <code>String</code> objects to specify conditions of data to select from table
	* @return <code>SelectReturn</code> object containing data from query
	* @throws SQLException If tablename is invalid, columns list is invalid length or conditions list contains invalid condition(s)
	**/
	public static SelectReturn selectRecords(Connection c, String tablename,
			ArrayList<String> columns, ArrayList<String> conditions) throws SQLException {
		
		SelectBuilder sb = new SelectBuilder(tablename, columns, conditions);
		
		Statement sqlStatement = c.createStatement();
		
		System.out.println(sb.getStatement());
		
		ResultSet rs = sqlStatement.executeQuery(sb.getStatement());
		
		List<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		
		while (rs.next()) {
			//String id = Integer.toString(rs.getInt("id"));
			String name = rs.getString("name");
			String age = Integer.toString(rs.getInt("age"));
			String address = rs.getString("address");
			String salary = Float.toString(rs.getFloat("salary"));
			
			values.add(new ArrayList<String>(Arrays.asList(name, age, address, salary)));
		}
		
		rs.close();
		sqlStatement.close();
		
		return new SelectReturn(c, values);
		
	}
}
