package gui;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datastructures.SelectReturn;
import entities.Employee;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import launcher.DatabaseFunctions;

public class DatabaseGUI extends Application {
	
	private static final int SCENE_WIDTH = 750;
	private static final int SCENE_HEIGHT = 500;
	
	private static final String TABLE_TITLE = "Employees";
	
	private String PATH = null;

	private VBox root;
	private TableView table = new TableView();
	
	private Connection c;
	

	@Override
	public void start(Stage stage) throws Exception {
		
		this.c = null;
		
		this.setupGUI(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	* Sets GUI up with settings
	* @param stage - <code>Stage</code> to use
	**/
	private void setupGUI(Stage stage) {
		stage.setTitle("Database Manager");
		this.root = new VBox();
		
		Menu fileMenu = new Menu("File");
		
		MenuItem openItem = new MenuItem("Open");
		MenuItem closeItem = new MenuItem("Close");
		
		closeItem.setDisable(true);
		
		fileMenu.getItems().addAll(openItem, closeItem);
		
		MenuBar menuBar = new MenuBar();
		
		menuBar.getMenus().addAll(fileMenu);
		
		this.root.getChildren().addAll(menuBar);
		
		this.createLabel();
		this.createTable();
		
		stage.setScene(new Scene(this.root, SCENE_WIDTH, SCENE_HEIGHT));
		
		openItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose db file");
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DATABASE files (*.db)", "*.db");
				
				fileChooser.getExtensionFilters().add(extFilter);
				
				File file = fileChooser.showOpenDialog(stage);
				
				if (file != null) {
					PATH = file.getPath();
					closeItem.setDisable(false);
					performOperations();
				}
			}
		});
		
		closeItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				closeItem.setDisable(true);
				PATH = null;
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Database Connection Closed!");
			}
		});
		
		stage.show();
		
	}
	
	/**
	* Gets list of data from SELECT statement and converts to List of <code>Employee</code> objects
	* @param values - values from SELECT statement
	* @return <code>ObservableList</code> of <code>Employee</code> objects
	**/
	private ObservableList<Employee> getDataList(List<ArrayList<String>> values) {
		List<Employee> employees = new ArrayList<Employee>();
		
		for (int i=0;i<values.size();i++) {
			employees.add(new Employee(values.get(i).get(0),
					values.get(i).get(1),
					values.get(i).get(2),
					values.get(i).get(3)));
		}
		
		return FXCollections.observableArrayList(employees);
	}

	/**
	* Creates empty table and adds to <code>VBox</code>
	**/
	private void createTable() {
		this.table.setEditable(true);
		
		this.root.getChildren().add(this.table);
	}
	
	/**
	* Adds columns to table of VBox
	* @param columns - <code>List</code> of <code>String</code> objects
	* @param values - <code>List</code> of <code>ArrayList</code> of <code>String</code> objects to use as values
	**/
	private void addColumns(List<String> columns, List<ArrayList<String>> values) {
		this.table.getColumns().clear();
		
		List<TableColumn> tableColumns = new ArrayList<TableColumn>();
		List<String> names = new ArrayList<String>(
				Arrays.asList("name", "age", "address", "salary"));
		
		for (String s : columns) {
			tableColumns.add(new TableColumn(s));
		}
		
		for (int i=0;i<tableColumns.size();i++) {
			tableColumns.get(i).setCellValueFactory(
					new PropertyValueFactory<Employee, String>(names.get(i)));
		}
		
		ObservableList<Employee> data = this.getDataList(values);
		
		this.table.setItems(data);
		
		this.table.getColumns().addAll(tableColumns);
	}
	
	/**
	* Creates default label and adds to table
	**/
	private void createLabel() {
		final Label label = new Label(TABLE_TITLE);
		
		label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		this.root.getChildren().add(label);
	}
	
	/**
	* Tester method for performing operations
	**/
	private void performOperations() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:" + PATH);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Database Connection Opened!");
		
		String tablename = "Employee";
		
		ArrayList<String> columns = new ArrayList<String>(Arrays.asList("NAME", "AGE", "ADDRESS", "SALARY"));
		ArrayList<String> types = new ArrayList<String>(Arrays.asList("TEXT", "INT", "CHAR(50)", "REAL"));
		ArrayList<Boolean> nulls = new ArrayList<Boolean>(Arrays.asList(false, false, true, false));
		
		ArrayList<String> values = new ArrayList<String>(Arrays.asList("'Craig'", "19", "'22 The Russetts'", "200000.00"));
		
		List<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
		
		try {
			//c = DatabaseFunctions.dropTable(c, tablename);
			c = DatabaseFunctions.createTable(c, tablename, columns, types, nulls);
			c = DatabaseFunctions.insertRecord(c, tablename, values);
			SelectReturn returns = DatabaseFunctions.selectRecords(c, tablename, new ArrayList<String>(), new ArrayList<String>());
			c = returns.getConnection();
			records = returns.getValues();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.addColumns(columns, records);
		
		System.out.println("Database Operations successful!");
		
		
	}
	
}
