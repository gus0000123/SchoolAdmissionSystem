package com.mcit.kritth.model.data;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="All_Student_Grades")
public class StudentGrade
{
	@Id
	@SequenceGenerator(name="sg_id_gen", sequenceName="student_grade_id_seq")
	@GeneratedValue	(strategy=GenerationType.SEQUENCE, generator="sg_id_gen")
	private int gradeId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_date = new Date();
	
	@OneToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(name="as_student_grades_marks",
		joinColumns={@JoinColumn(name="student_grade_id")},
		inverseJoinColumns={@JoinColumn(name="course_mark_id")})
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
}
