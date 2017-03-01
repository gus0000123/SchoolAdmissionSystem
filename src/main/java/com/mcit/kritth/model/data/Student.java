package com.mcit.kritth.model.data;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Students")
public class Student
{
	@Id
	private int id;
	private String major;
	private String minor;
	private int credit = 0;
	private int year = 0;
	@Temporal(TemporalType.DATE)
	private Date start_date = new Date();

	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	private Department department;

	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	private StudentAdmissionStatus admission_status;
	
	@OneToOne
	@JoinColumn(name="person_id")
	@NotNull
	private Person person;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="as_student_enrolled_courses",
		joinColumns={@JoinColumn(name="student_id")},
		inverseJoinColumns={@JoinColumn(name="course_id")})
	private Set<Course> enrolled_courses;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<StudentGrade> marks;
	
	public Student() { }
	
	public String getMajor() { return this.major; }
	public String getMinor() { return this.minor; }
	public int getCredit() { return this.credit; }
	public Date getStart_date() { return this.start_date; }
	public Person getPerson() { return this.person; }
	
	public void setMajor(String in) { this.major = in; }
	public void setMinor(String in) { this.minor = in; }
	public void setCredit(int in) { this.credit = in; }
	public void setStart_date(Date in) { this.start_date = in; }
	public void setPerson(Person in) {
		this.person = in;
		this.id = this.person.getID();
	}

	@Override
	public String toString()
	{
		return person.toString() + " ;" +
				admission_status.toString() + " ;" +
				major + " ;" +
				minor + " ;" +
				credit + " ;" +
				start_date.toString();
	}
	
	public Set<StudentGrade> getMarks() {
		if (marks == null) marks = new HashSet<StudentGrade>();
		return marks;
	}

	public void setMarks(Set<StudentGrade> marks) {
		this.marks = marks;
	}

	public Set<Course> getEnrolled_courses() {
		if (enrolled_courses == null) enrolled_courses = new HashSet<>();
		return enrolled_courses;
	}

	public void setEnrolled_courses(Set<Course> enrolled_courses) {
		this.enrolled_courses = enrolled_courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public int hashCode() {
		return ("student:" + id).hashCode();
	}
	
	@Override
	public boolean equals(Object s) {
		if (s instanceof Student)
			return this.getId() == ((Student) s).getId();
		else
			return false;
	}

	public StudentAdmissionStatus getAdmission_status() {
		return admission_status;
	}

	public void setAdmission_status(StudentAdmissionStatus admission_status) {
		this.admission_status = admission_status;
	}
}