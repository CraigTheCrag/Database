package entities;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee extends Entity {
	
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty address;
	private SimpleFloatProperty salary;

	public Employee(int id, String name, int age, String address, float salary) {
		super(id);
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		this.address = new SimpleStringProperty(address);
		this.salary = new SimpleFloatProperty(salary);
	}

	public String getName() {
		return this.name.get();
	}

	public int getAge() {
		return this.age.get();
	}

	public String getAddress() {
		return this.address.get();
	}

	public float getSalary() {
		return this.salary.get();
	}

}
