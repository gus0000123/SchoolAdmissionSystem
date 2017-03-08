package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.mcit.kritth.model.Bean;

@Entity
@Table(name = "Person")
public class Person implements Bean
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
	private Address address = new Address();
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
	
	public String getFullName()
	{
		return firstName + " "
				+ (middleName != null ? middleName + " " : "")
				+ lastName;
	}
	
	@Override
	public String toString()
	{
		return ID + " ;" + 
				firstName + " ;" +
				middleName + " ;" +
				lastName + " ;" +
				(address != null ? address.toString() + " ;" : "") +
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Person)
		{
			return ((Person) o).getID() == this.getID();
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void copy(Bean b) {
		if (b instanceof Person)
		{
			this.setSin(((Person) b).getSin());
			this.setGender(((Person) b).getGender());
			this.setEmail(((Person) b).getEmail());
			this.setFirstName(((Person) b).getFirstName());
			this.setLastName(((Person) b).getLastName());
			this.setTelNo(((Person) b).getTelNo());
			this.setMiddleName(((Person) b).getMiddleName());
			// Address is handled explicitly
		}
	}
}
