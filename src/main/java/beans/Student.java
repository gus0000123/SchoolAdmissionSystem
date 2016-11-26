package beans;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.apache.commons.beanutils.*;

@Entity
@Table(name="Students")
public class Student extends Person
{
	@ManyToOne(cascade=CascadeType.ALL)
	@NotNull							private StudentAdmissionStatus admission_status;
	@NotNull							private String major;
										private String minor;
										private int credit = 0;
	@Temporal(TemporalType.TIMESTAMP)	private Date start_date = new Date();
	@OneToOne
	@JoinColumn(name="person_id")		private Person person;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Student_Grades",
		joinColumns={@JoinColumn(name="student_id")},
		inverseJoinColumns={@JoinColumn(name="mark_id")})
										private List<StudentGrade> marks;
	
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

	public List<StudentGrade> getMarks() {
		if (marks == null) marks = new ArrayList<StudentGrade>();
		return marks;
	}

	public void setMarks(List<StudentGrade> marks) {
		this.marks = marks;
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
}