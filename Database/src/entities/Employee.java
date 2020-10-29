package entities;

import javafx.beans.property.SimpleStringProperty;

public class Employee{
	
	/*
		I have no clue how this works :)
	*/
	
	private SimpleStringProperty name;
	private SimpleStringProperty age;
	private SimpleStringProperty address;
	private SimpleStringProperty salary;

	/**
	* Constructor using name, age, address and salary
	* @param name - Name of employee
	* @param age - Age of employee
	* @param address - Address of Employee
	* @param salary - Salary of employee
	**/
	public Employee(String name, String age, String address, String salary) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
		this.address = new SimpleStringProperty(address);
		this.salary = new SimpleStringProperty(salary);
	}
	
	/**
	* Constructor using name, age and address
	* @param name - Name of employee
	* @param age - Age of employee
	* @param address - Address of employee
	**/
	public Employee(String name, String age, String address) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
		this.address = new SimpleStringProperty(address);
	}
	
	/**
	* Constructor using name, age
	* @param name - Name of employee
	* @param age - Age of employee
	**/
	public Employee(String name, String age) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
	}
	
	/**
	* Constructor using name
	* @param name - Name of employee
	**/
	public Employee(String name) {
		this.name = new SimpleStringProperty(name);
	}

	/**
	* Default Constructor
	**/
	public Employee() {}

	/**
	* Getter method for employee name
	* @return name of employee as <code>String</code>
	**/
	public String getName() {
		return this.name.get();
	}

	/**
	* Getter method for employee age
	* @return age of employee as <code>String</code>
	**/
	public String getAge() {
		return this.age.get();
	}

	/**
	* Getter method for employee address
	* @return address of employee as <code>String</code>
	**/
	public String getAddress() {
		return this.address.get();
	}

	/**
	* Getter method for employee salary
	* @return salary of employee as <code>String</code>
	**/
	public String getSalary() {
		return this.salary.get();
	}
}
