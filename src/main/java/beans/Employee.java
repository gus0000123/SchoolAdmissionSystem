package beans;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="Employees")
public class Employee extends Person
{
	@Column(name="salary") 			private double salary;
	@Column(name="rank")			private int rank;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")		private Date start_date;
	@OneToOne
	@JoinColumn(name="person_id")	private Person person;
	
	public Date getStart_date() { return start_date; }
	public int getRank() { return rank; }
	public double getSalary() { return salary; }
	public Person getPerson() { return person; }
	
	public void setStart_date(Date start_date) { this.start_date = start_date; }
	public void setRank(int rank) { this.rank = rank; }
	public void setSalary(double salary) { this.salary = salary; }
	public void setPerson(Person person) { this.person = person; }
	
	@Override
	public String toString()
	{
		return person.toString() + " ;" +
				salary + " ;" +
				rank + " ;" +
				start_date.toString();
	}
}