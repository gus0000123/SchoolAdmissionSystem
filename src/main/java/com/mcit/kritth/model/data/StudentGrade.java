package com.mcit.kritth.model.data;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="All_Student_Grades")
public class StudentGrade
{
	@Id
	@SequenceGenerator(name="sg_id_gen", sequenceName="student_grade_id_seq")
	@GeneratedValue	(strategy=GenerationType.SEQUENCE, generator="sg_id_gen")
	private int gradeId;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_date = new Date();
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student_id")
	private Student student;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	private Set<CourseMark> courseMarks;
	// TODO: private Attendance attendance;
	
	public StudentGrade() { }
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Set<CourseMark> getCourseMarks() {
		if (courseMarks == null) courseMarks = new HashSet<CourseMark>();
		return courseMarks;
	}
	public void setCourseMarks(Set<CourseMark> courseMarks) {
		this.courseMarks = courseMarks;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
