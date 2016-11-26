package beans;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

import util.BeanUtil;

@Entity
@Table(name="Courses")
public class Course
{
	@Id @GeneratedValue					private int course_id;
	@NotNull							private int class_level = 1;
	@NotNull							private int course_number = 0;
	@NotNull							private int section = 1;
	@NotNull							private String course_name;
	@OneToOne
	@JoinColumn(name="dept_id")
	@NotNull 							private Department department;
	@ManyToOne(cascade=CascadeType.ALL)
	@NotNull 							private Employee instructor;
	@Column(unique = true)				private String course_code;		// Use BeanUtil.getCourseCode
										private String course_description;
										private int capacity = 0;
										private int credit = 3;
	// TODO: private Map<DayOfWeek, ScheduleTime> schedule;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Course_Pre_Requisite",
		joinColumns={@JoinColumn(name="course_id")},
		inverseJoinColumns={@JoinColumn(name="pre_req_id")})
										private List<Course> prerequisite;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Course_TA",
		joinColumns={@JoinColumn(name="course_id")},
		inverseJoinColumns={@JoinColumn(name="employee_id")})
										private List<Employee> ta;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Enrolled_Student",
		joinColumns={@JoinColumn(name="course_id")},
		inverseJoinColumns={@JoinColumn(name="student_id")})
										private List<Student> students;

	public Course() { }
	
	public String getCourse_name() {
		course_name = BeanUtil.getCourseCode(this);
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
	public List<Course> getPrerequisite() {
		if (prerequisite == null) prerequisite = new ArrayList<Course>();
		return prerequisite;
	}
	public void setPrerequisite(List<Course> prerequisite) {
		this.prerequisite = prerequisite;
	}
	public Employee getInstructor() {
		return instructor;
	}
	public void setInstructor(Employee instructor) {
		this.instructor = instructor;
	}
	public List<Employee> getTa() {
		if (ta == null) ta = new ArrayList<Employee>();
		return ta;
	}
	public void setTa(List<Employee> ta) {
		this.ta = ta;
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
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
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

	public List<Student> getStudents() {
		if (students == null) students = new ArrayList<Student>();
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
