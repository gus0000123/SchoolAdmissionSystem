package com.mcit.kritth.model.data;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Works")
public class CourseWork
{
	@Id
	@GeneratedValue(generator="COURSEWORK_SEQ_GEN",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="coursework_sequence_name",name="COURSEWORK_SEQ_GEN",allocationSize=1)
	private int coursework_id;
	@NotNull							
	private String coursework_name;
	@NotNull							
	private String coursework_description;
	@NotNull							
	private double contribution;
	@NotNull							
	private int max_mark;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull 					
	private Course course;
	
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public int hashCode()
	{
		return (new Integer(this.coursework_id)).hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof CourseWork) return this.coursework_id == ((CourseWork) obj).getCoursework_id();
		else return false;
	}
}
