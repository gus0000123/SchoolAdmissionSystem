package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Marks")
public class CourseMark
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int coursemark_id;
	@NotNull							
	private int mark;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull 							
	private CourseWork coursework;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@NotNull 							
	private Student student;
	
	public CourseMark() { }


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
	
	public int getCoursemark_id() {
		return coursemark_id;
	}


	public void setCoursemark_id(int coursemark_id) {
		this.coursemark_id = coursemark_id;
	}
	
	@Override
	public int hashCode()
	{
		return (new Integer(this.coursemark_id)).hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof CourseMark) return this.coursemark_id == ((CourseMark) obj).getCoursemark_id();
		else return false;
	}
}
