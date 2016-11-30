package bean.data;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.apache.commons.beanutils.*;

@Entity
@Table(name="Students")
public class Student extends Person
{
	@NotNull
	private String major;
	private String minor;
	private int credit = 0;
	@Temporal(TemporalType.DATE)
	private Date start_date = new Date();
	
	@ManyToOne
	@NotNull
	private StudentAdmissionStatus admission_status;
	
	@OneToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="as_student_enrolled_courses",
		joinColumns={@JoinColumn(name="student_id")},
		inverseJoinColumns={@JoinColumn(name="course_id")})
	private Set<Course> enrolled_courses;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="as_student_grades",
		joinColumns={@JoinColumn(name="student_id")},
		inverseJoinColumns={@JoinColumn(name="student_grade_id")})
	private Set<StudentGrade> marks;
	
	public Student() { }
	
	public StudentAdmissionStatus getAdmissionStatus() { return this.admission_status; }
	public String getMajor() { return this.major; }
	public String getMinor() { return this.minor; }
	public int getCredit() { return this.credit; }
	public Date getStartDate() { return this.start_date; }
	public Person getPerson() { return this.person; }
	
	public void setAdmissionStatus(StudentAdmissionStatus in) { this.admission_status = in; }
	public void setMajor(String in) { this.major = in; }
	public void setMinor(String in) { this.minor = in; }
	public void setCredit(int in) { this.credit = in; }
	public void setStartDate(Date in) { this.start_date = in; }
	public void setPerson(Person in)
	{
		try
		{
			this.person = in;
			BeanUtils.copyProperties(this, person);
		}
		catch (IllegalAccessException|InvocationTargetException e)
		{
			e.printStackTrace();
			this.person = null;
		}
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
}