package entities;

import javafx.beans.property.SimpleStringProperty;

public class Employee{
	
	private SimpleStringProperty name;
	private SimpleStringProperty age;
	private SimpleStringProperty address;
	private SimpleStringProperty salary;

	public Employee(String name, String age, String address, String salary) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
		this.address = new SimpleStringProperty(address);
		this.salary = new SimpleStringProperty(salary);
	}
	
	public Employee(String name, String age, String address) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
		this.address = new SimpleStringProperty(address);
	}
	
	public Employee(String name, String age) {
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleStringProperty(age);
	}
	
	public Employee(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getName() {
		return this.name.get();
	}

	public String getAge() {
		return this.age.get();
	}

	public String getAddress() {
		return this.address.get();
	}

	public String getSalary() {
		return this.salary.get();
	}

}
