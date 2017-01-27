package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.bo.template.StudentGradeBO;
import com.mcit.kritth.dao.template.StudentDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentGrade;

@Service("studentService")
@Transactional
public class StudentBOImpl implements StudentBO
{
	@Autowired
	private StudentDAO dao;
	
	@Autowired
	private DepartmentBO dservice;
	
	@Autowired
	private CourseBO cservice;
	
	@Autowired
	private StudentGradeBO sgservice;
	
	@Autowired
	private CourseMarkBO cmservice;
	
	@Override
	public void insert(Student o) { dao.insertBean(o); }

	@Override
	public void update(Student o)
	{
		Student s = getById(o.getId());
		
		Set<Course> oldCourses = s.getEnrolled_courses();
		
		s.setMajor(o.getMajor());
		s.setMinor(o.getMinor());
		s.setDepartment(o.getDepartment());
		s.setAdmissionStatus(o.getAdmissionStatus());
		s.setEnrolled_courses(o.getEnrolled_courses());
		
		dao.updateBean(s);
		
		// Check if course changed
		updateCourses(s, oldCourses);
	}

	@Override
	public void delete(Student o)
	{
		// Delete from department
		if (o.getDepartment().getStudents() != null && o.getDepartment().getStudents().size() > 0)
		{
			o.getDepartment().getStudents().remove(o);
			dservice.update(o.getDepartment());
		}
		
		// Delete from course
		for (Course c : o.getEnrolled_courses())
		{
			c.getStudents().remove(o);
			cservice.update(c);
		}
		
		dao.removeBeanByPrimaryKey(o.getId());
	}

	@Override
	public Student getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAllBeans(); }
	
	// this is only created/delete when course is added or removed
	private void updateStudentGrade(Student s, Course c)
	{		
		if (!s.getEnrolled_courses().contains(c))
		{
			// Deleting student grades from this course
			StudentGrade toDelete = null;
			
			for (StudentGrade sg : s.getMarks())
			{
				if (sg.getCourse().equals(c))
				{
					toDelete = sg;
					break;
				}
			}
			
			if (toDelete != null)
			{
				s.getMarks().remove(toDelete);
				update(s);
				try
				{
					// delete this will delete whole hierarchy
					sgservice.delete(toDelete);
				}
				catch (Exception e) { }
			}
		}
		else
		{
			// Adding the student grade 
			StudentGrade sg = new StudentGrade();
			
			sg.setStudent(s);
			sg.setCourse(c);
			
			// Adding course mark
			for (CourseWork cw : c.getCourse_works())
			{
				CourseMark cm = new CourseMark();
				
				cm.setMark(0);
				cm.setCoursework(cw);
				cm.setStudent(s);
				
				cmservice.insert(cm);
				
				sg.getCourseMarks().add(cm);
			}
			
			sgservice.insert(sg);
			
			s.getMarks().add(sg);
			
			update(s);
		}
	}
	
	private void updateCourses(Student newStudent, Set<Course> oldCourses)
	{
		if (oldCourses != null)
		{
			// Delete student from old courses
			for (Course c : oldCourses)
			{
				if (!newStudent.getEnrolled_courses().contains(c))
				{
					c.getStudents().remove(newStudent);
					cservice.update(c);
					updateStudentGrade(newStudent, c);
				}
			}
			
			// Add student to new course
			for (Course c : newStudent.getEnrolled_courses())
			{
				if (!oldCourses.contains(c))
				{
					c.getStudents().add(newStudent);
					cservice.update(c);
					updateStudentGrade(newStudent, c);
				}
			}
		}
		else
		{
			for (Course c : newStudent.getEnrolled_courses())
			{
				c.getStudents().remove(newStudent);
				cservice.update(c);
			}
		}
	}
}
