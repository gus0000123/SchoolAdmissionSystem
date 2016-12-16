package com.mcit.kritth.model.data;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Employees")
public class Employee
{
	@Id
	private int id;
	
	@NotNull
	@Column(name="salary")
	private double salary = 0;
	@Column(name="rank")
	private int rank = 5;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date start_date = new Date();
	
	@NotNull
	@OneToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@OneToMany(mappedBy="instructor",fetch=FetchType.EAGER)
	private Set<Course> assigned_courses;

	@ManyToOne(fetch=FetchType.EAGER)
	private Department department;
	
	// TODO: Add pay cheque
	
	public Employee() { }
	
	public Date getStart_date() { return start_date; }
	public int getRank() { return rank; }
	public double getSalary() { return salary; }
	public Person getPerson() { return person; }
	
	public void setStart_date(Date start_date) { this.start_date = start_date; }
	public void setRank(int rank) { this.rank = rank; }
	public void setSalary(double salary) { this.salary = salary; }
	public void setPerson(Person in) {
		this.person = in;
		this.id = in.getID();
	}
	
	@Override
	public String toString()
	{
		return person.toString() + " ;" +
				salary + " ;" +
				rank + " ;" +
				start_date.toString();
	}

	public Set<Course> getAssigned_courses() {
		if (assigned_courses == null) assigned_courses = new HashSet<Course>();
		return assigned_courses;
	}

	public void setAssigned_courses(Set<Course> assigned_courses) {
		this.assigned_courses = assigned_courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}