package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Marks")
public class CourseMark
{
	@Id @GeneratedValue					
	private int id;
	@NotNull							
	private int mark;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="course_work_id")
	@NotNull 							
	private CourseWork coursework;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="student_id") 
	@NotNull 							
	private Student student;
	
	public CourseMark() { }
										
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public CourseWork getCoursework() {
		return coursework;
	}

	public void setCoursework(CourseWork coursework) {
		this.coursework = coursework;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
}
