package org.onbrains.onwork.env.employee.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import org.onbrains.onwork.inf.modelbase.BusinessObject;

@Entity
@Access(AccessType.FIELD)
@Table(schema = "system")
public class Employee extends BusinessObject {

	private static final long serialVersionUID = 1746095805020111784L;

	@Column(name = "last_name", length = 64, nullable = false)
	private String lastName;

	@Column(name = "first_name", length = 32, nullable = false)
	private String firstName;

	@Column(name = "second_name", length = 32)
	private String secondName;

	private boolean male;

	@Version
	private Long version;

	protected Employee() {
	}

	public Employee(Long id) {
		super(id);
	}

	@Override
	public String getObjectName() {
		StringBuilder objectName = new StringBuilder(String.format("%s %s.", lastName, firstName.substring(0, 1)));
		if (!secondName.isEmpty())
			objectName.append(String.format("%s.", secondName.substring(0, 1)));
		return objectName.toString();
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

}