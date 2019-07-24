package de.clumsystuff.mysql.transactions;

public class DataObject {

	private final String firstName;
	private final String lastName;

	public DataObject(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
