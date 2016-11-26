package beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Course_Marks")
public class CourseMark
{
	@Id @GeneratedValue					private int id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="course_work_id")
	@NotNull 							private CourseWork coursework;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="student_id") 
	@NotNull 							private Student student;
	@NotNull							private int mark;
	
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
