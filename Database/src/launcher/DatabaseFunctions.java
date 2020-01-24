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
	
	public static Connection dropTable(Connection c, String tablename) throws SQLException {
		TableDestroyer td = new TableDestroyer(tablename);
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(td.getStatement());
		
		return c;
	}
	
	public static Connection createTable(Connection c, String tablename, 
			ArrayList<String> columns, ArrayList<String> types, 
			ArrayList<Boolean> nulls) throws SQLException {
		
		TableBuilder tb = new TableBuilder(tablename, columns, types, nulls);
		
		System.out.println(tb.getStatement());
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(tb.getStatement());
		
		return c;
	}
	
	public static Connection insertRecord(Connection c, String tablename,
			ArrayList<String> values) throws SQLException {
		
		InsertBuilder ib = new InsertBuilder(tablename, values);
		
		System.out.println(ib.getStatement());
		
		Statement sqlStatement = c.createStatement();
		sqlStatement.executeUpdate(ib.getStatement());
		
		return c;
		
	}
	
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
