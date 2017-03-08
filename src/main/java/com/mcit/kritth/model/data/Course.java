package com.mcit.kritth.model.data;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.mcit.kritth.model.Bean;
import com.mcit.kritth.util.BeanUtil;

@Entity
@Table(name="Courses", uniqueConstraints=@UniqueConstraint(columnNames={"course_code"}))
public class Course implements Bean
{
	@Id
	private String course_code;		// Use BeanUtil.getCourseCode
	@NotNull
	private int class_level = 1;
	@NotNull
	private int course_number = 0;
	@NotNull
	private int section = 1;
	@NotNull
	private String course_name;
	private String course_description;
	private int capacity = 10;
	private int credit = 3;
	private boolean is_active = true;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	private Department department;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	private Employee instructor;

	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<CourseWork> course_works;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="as_course_pre_requisite",
		joinColumns={@JoinColumn(name="course_id")},
		inverseJoinColumns={@JoinColumn(name="pre_req_id")})
	private Set<Course> prerequisite;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="as_registered_students",
		joinColumns={@JoinColumn(name="course_id")},
		inverseJoinColumns={@JoinColumn(name="student_id")})
	private Set<Student> students;

	
	public Course() { }
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_description() {
		return course_description;
	}
	public void setCourse_description(String course_description) {
		this.course_description = course_description;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Set<Course> getPrerequisite() {
		if (prerequisite == null) prerequisite = new HashSet<Course>();
		return prerequisite;
	}
	public void setPrerequisite(Set<Course> prerequisite) {
		this.prerequisite = prerequisite;
	}
	public Employee getInstructor() {
		return instructor;
	}
	public void setInstructor(Employee instructor) {
		this.instructor = instructor;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCourse_number() {
		return course_number;
	}
	public void setCourse_number(int course_number) {
		this.course_number = course_number;
	}
	public int getClass_level() {
		return class_level;
	}
	public void setClass_level(int class_level) {
		this.class_level = class_level;
	}
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Student> getStudents() {
		if (students == null) students = new HashSet<Student>();
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Set<CourseWork> getCourse_works() {
		if (course_works == null) course_works = new HashSet<CourseWork>();
		return course_works;
	}

	public void setCourse_works(Set<CourseWork> course_works) {
		this.course_works = course_works;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Course)
			return this.hashCode() == ((Course) o).hashCode();
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return ("course:" + this.class_level + "" +  this.course_number + "" +  this.section + ":" + this.course_name).hashCode();
	}

	@Override
	public void copy(Bean b) {
		if (b instanceof Course)
		{
			this.setClass_level(((Course) b).getClass_level());
			this.setCourse_number(((Course) b).getCourse_number());
			this.setSection(((Course) b).getSection());
			this.setCourse_code(((Course) b).getCourse_code());
			this.setCourse_name(((Course) b).getCourse_name());
			this.setCourse_description(((Course) b).getCourse_description());
			this.setCapacity(((Course) b).getCapacity());
			this.setPrerequisite(((Course) b).getPrerequisite());
			this.setInstructor(((Course) b).getInstructor());
			this.setCredit(((Course) b).getCredit());
			this.setStudents(((Course) b).getStudents());
			this.setIs_active(((Course) b).isIs_active());
			this.setCourse_works(((Course) b).getCourse_works());
		}
	}
}
