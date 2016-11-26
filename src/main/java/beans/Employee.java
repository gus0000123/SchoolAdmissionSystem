package beans;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.beanutils.BeanUtils;

@Entity
@Table(name="Employees")
public class Employee extends Person
{
	@NotNull
	@Column(name="salary") 			private double salary;
	@Column(name="rank")			private int rank = 5;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")		private Date start_date = new Date();
	@OneToOne
	@JoinColumn(name="person_id")	private Person person;
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
}