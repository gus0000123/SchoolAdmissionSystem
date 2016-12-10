package com.mcit.kritth.model.data;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Works")
public class CourseWork
{
	@Id
	@SequenceGenerator(name="cw_id_gen", sequenceName="course_works_id_seq")
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="cw_id_gen")				
	private int coursework_id;
	@NotNull							
	private String coursework_name;
	@NotNull							
	private String coursework_description;
	@NotNull							
	private double contribution;
	@NotNull							
	private int max_mark;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation_date = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;
	
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

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
