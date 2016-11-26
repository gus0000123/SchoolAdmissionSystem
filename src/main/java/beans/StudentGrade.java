package beans;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="All_Student_Grades")
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
												private Set<CourseMark> courseMarks;
	@Temporal(TemporalType.TIMESTAMP)			private Date start_date = new Date();
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
