package gui;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

	private void createTable() {
		this.table.setEditable(true);
		
		TableColumn idColumn = new TableColumn("ID");
		TableColumn nameColumn = new TableColumn("Name");
		
		this.table.getColumns().addAll(idColumn, nameColumn);
		
		this.root.getChildren().add(this.table);
	}
	
	private void createLabel() {
		final Label label = new Label(TABLE_TITLE);
		
		label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		this.root.getChildren().add(label);
	}
	
	private void performOperations() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:" + PATH);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Database Connection Opened!");
		
		String tablename = "EMPLOYEE";
		/*ArrayList<String> columns = new ArrayList<String>(Arrays.asList("ID", "NAME", "AGE", "ADDRESS", "SALARY"));
		ArrayList<String> types = new ArrayList<String>(Arrays.asList("INT", "TEXT", "INT", "CHAR(50)", "REAL"));
		ArrayList<Boolean> nulls = new ArrayList<Boolean>(Arrays.asList(false, false, false, true, false));
		
		ArrayList<String> values = new ArrayList<String>(Arrays.asList("0", "'Craig'", "19", "'22 The Russetts'", "200000.00"));
		
		try {
			c = DatabaseFunctions.createTable(c, tablename, columns, types, nulls);
			c = DatabaseFunctions.insertRecord(c, tablename, values);
			c = DatabaseFunctions.selectRecords(c, tablename, new ArrayList<String>(), new ArrayList<String>());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		System.out.println("Database Operations successful!");
	}
	
}
