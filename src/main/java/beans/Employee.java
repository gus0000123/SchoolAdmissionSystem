package beans;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.beanutils.BeanUtils;

@Entity
@Table(name="Employees")
public class Employee extends Person
{
	@NotNull
	@Column(name="salary") 					private double salary;
	@Column(name="rank")					private int rank = 5;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")				private Date start_date = new Date();
	@OneToOne
	@JoinColumn(name="person_id")			private Person person;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="instructor")
											private List<Course> assigned_courses;
	// TODO: Add pay cheque
	
	public Employee() { }
	
	public Date getStart_date() { return start_date; }
	public int getRank() { return rank; }
	public double getSalary() { return salary; }
	public Person getPerson() { return person; }
	
	public void setStart_date(Date start_date) { this.start_date = start_date; }
	public void setRank(int rank) { this.rank = rank; }
	public void setSalary(double salary) { this.salary = salary; }
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
				salary + " ;" +
				rank + " ;" +
				start_date.toString();
	}

	public List<Course> getAssigned_courses() {
		if (assigned_courses == null) assigned_courses = new ArrayList<Course>();
		return assigned_courses;
	}

	public void setAssigned_courses(List<Course> assigned_courses) {
		this.assigned_courses = assigned_courses;
	}
}