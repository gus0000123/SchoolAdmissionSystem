package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="StudentGrades")
public class StudentGrade
{
	@Id
	@GeneratedValue								private int gradeId;
	@OneToOne
	@JoinColumn(name="student_id")				private Student student;
	@OneToOne
	@JoinColumn(name="course_id")				private Course course;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Student_Grades_Marks",
		joinColumns={@JoinColumn(name="student_grade_id")},
		inverseJoinColumns={@JoinColumn(name="mark_id")})
												private List<CourseMark> courseMarks;
	@Temporal(TemporalType.TIMESTAMP)			private Date start_date;
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
	public List<CourseMark> getCourseMarks() {
		if (courseMarks == null) courseMarks = new ArrayList<CourseMark>();
		return courseMarks;
	}
	public void setCourseMarks(List<CourseMark> courseMarks) {
		this.courseMarks = courseMarks;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
}
