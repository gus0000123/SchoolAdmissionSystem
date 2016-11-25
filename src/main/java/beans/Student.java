package beans;

import java.util.Date;

import javax.persistence.*;

import enums.AdmissionStatus;

@Entity
@Table(name="Students")
public class Student extends Person
{
	@Column(name="admission_status") 	private AdmissionStatus admission_status = AdmissionStatus.PENDING;
	@Column(name="major")				private String major;
	@Column(name="minor")				private String minor;
	@Column(name="credit")				private int credit;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")			private Date start_date = new Date();
	@OneToOne
	@JoinColumn(name="person_id")		private Person person;
	
	public Student() { }
	
	@Enumerated(EnumType.STRING)
	public AdmissionStatus getAdmissionStatus() { return this.admission_status; }
	public String getMajor() { return this.major; }
	public String getMinor() { return this.minor; }
	public int getCredit() { return this.credit; }
	public Date getStartDate() { return this.start_date; }
	public Person getPerson() { return this.person; }
	
	public void setAdmissionStatus(AdmissionStatus in) { this.admission_status = in; }
	public void setMajor(String in) { this.major = in; }
	public void setMinor(String in) { this.minor = in; }
	public void setCredit(int in) { this.credit = in; }
	public void setStartDate(Date in) { this.start_date = in; }
	public void setPerson(Person in) { this.person = in; }
	
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