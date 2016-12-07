package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Person")
public class Person
{
	@Id
	@SequenceGenerator(name="per_id_gen", sequenceName="person_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="per_id_gen")
	private int ID;
	
	@NotNull
	@Column(name="first_name")
	private String firstName;
	@Column(name="middle_name")
	private String middleName;
	@NotNull
	@Column(name="last_name")
	private String lastName;
	@Embedded
	private Address address;
	@Column(name="telephone")
	private String telNo;
	@NotNull
	@Column(name="email")
	private String email;
	@Column(name="gender")
	private char gender = 'M';
	@Column(name="sin")	
	private String sin;
	
	public Person() { }

	public String getSin() {
		return sin;
	}

	public void setSin(String sin) {
		this.sin = sin;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	@Override
	public String toString()
	{
		return ID + " ;" + 
				firstName + " ;" +
				lastName + " ;" +
				address.toString() + " ;" +
				telNo + " ;" +
				email + " ;" +
				gender + " ;" +
				sin;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}
