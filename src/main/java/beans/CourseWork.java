package beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Works")
public class CourseWork
{
	@Id
	@GeneratedValue						private int coursework_id;
	@ManyToOne(cascade=CascadeType.ALL)	private Course course;
	@NotNull							private String coursework_name;
	@NotNull							private String coursework_description;
	@NotNull							private double contribution;
	@NotNull							private int max_mark;
	
	public CourseWork() { }
	
	public int getMax_mark() {
		return max_mark;
	}
	public void setMax_mark(int max_mark) {
		this.max_mark = max_mark;
	}

	public String getCoursework_name() {
		return coursework_name;
	}

	public void setCoursework_name(String coursework_name) {
		this.coursework_name = coursework_name;
	}

	public String getCoursework_description() {
		return coursework_description;
	}

	public void setCoursework_description(String coursework_description) {
		this.coursework_description = coursework_description;
	}

	public double getContribution() {
		return contribution;
	}

	public void setContribution(double contribution) {
		this.contribution = contribution;
	}

	public int getCoursework_id() {
		return coursework_id;
	}

	public void setCoursework_id(int coursework_id) {
		this.coursework_id = coursework_id;
	}
}
