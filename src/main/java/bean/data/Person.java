package bean.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Person
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	
	@NotNull
	@Column(name="first_name")
	private String firstName;
	@Column(name="middle_name")
	private String middleName;
	@NotNull
	@Column(name="last_name")
	private String lastName;
	@Column(name="street_address")
	private String streetAddress;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="postal")
	private String postal;
	@Column(name="country")
	private String country;
	@Column(name="telephone")
	private String telNo;
	@NotNull
	@Column(name="email")
	private String email;
	@Column(name="gender")
	private char gender = 'M';
	@Column(name="sin")	
	private String sin;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dept_id")
	private Department department;
	
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

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		String address = streetAddress == null ? ""
				: streetAddress + ", " + city + ", " + state + ", " + postal; 
		return ID + " ;" + 
				firstName + " ;" +
				lastName + " ;" +
				address + " ;" +
				country + " ;" +
				telNo + " ;" +
				email + " ;" +
				gender + " ;" +
				sin;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}
