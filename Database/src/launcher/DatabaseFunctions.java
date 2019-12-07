package launcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import querybuilders.InsertBuilder;
import querybuilders.SelectBuilder;
import querybuilders.TableBuilder;

public class DatabaseFunctions {
	
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
	
	public static Connection selectRecords(Connection c, String tablename,
			ArrayList<String> columns, ArrayList<String> conditions) throws SQLException {
		
		SelectBuilder sb = new SelectBuilder(tablename, columns, conditions);
		
		Statement sqlStatement = c.createStatement();
		
		System.out.println(sb.getStatement());
		
		ResultSet rs = sqlStatement.executeQuery(sb.getStatement());
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			float salary = rs.getFloat("salary");
			
			System.out.println("ID = " + id);
			System.out.println("NAME = " + name);
			System.out.println("AGE = " + age);
			System.out.println("ADDRESS = " + address);
			System.out.println("SALARY = " + salary);
			System.out.println();
		}
		
		rs.close();
		sqlStatement.close();
		
		return c;
		
	}
}
