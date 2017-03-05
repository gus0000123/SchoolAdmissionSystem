package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.dao.template.StudentDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Student;

@Service("studentService")
@Transactional
public class StudentBOImpl implements StudentBO
{
	@Autowired
	private StudentDAO dao;
	
	@Autowired
	private CourseBO cservice;
	
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
		s.setAdmission_status(o.getAdmission_status());
		s.setEnrolled_courses(o.getEnrolled_courses());
		s.setMarks(o.getMarks());
		
		dao.updateBean(s);
		
		// Check if course changed
		updateCourses(s, oldCourses);
	}

	@Override
	public void delete(Student o)
	{		
		// Delete from course
		for (Course c : o.getEnrolled_courses())
		{
			c.getStudents().remove(o);
			cservice.update(c);
		}
		
		dao.removeBeanByPrimaryKey(o.getId());
	}

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public Student getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAllBeans(); }
	
	// this is only created/delete when course is added or removed
	private void updateStudentGrade(Student s, Course c)
	{	
		// Delete if not contain
		if (!s.getEnrolled_courses().contains(c))
		{
			Set<CourseMark> cmlistToDelete = new HashSet<>();
			List<CourseMark> cmlist = cmservice.getAll();
			
			for (CourseMark cm : cmlist)
			{
				if (cm.getCoursework().getCourse().equals(c)
						&& cm.getStudent().equals(s))
				{
					cmlistToDelete.add(cm);
				}
			}
			
			for (CourseMark cm : cmlistToDelete)
			{
				System.out.println("Deleting cm " + cm.getCoursemark_id() + " for cw " + cm.getCoursework().getCoursework_id());
				s.getMarks().remove(cm);
				try { cmservice.delete(cm); }
				catch (Exception e) { e.printStackTrace(); }
			}
			
			dao.updateBean(s);
		}
		else
		{
			// Adding course mark
			for (CourseWork cw : c.getCourse_works())
			{
				boolean found = false;
				for (CourseMark scm : s.getMarks())
				{
					if (scm.getCoursework().getCoursework_id() == cw.getCoursework_id())
					{
						found = true;
						break;
					}
					
				}
				
				if (!found)
				{
					CourseMark cm = new CourseMark();
					
					cm.setMark(0);
					cm.setCoursework(cw);
					cm.setStudent(s);
					
					cmservice.insert(cm);
					
					s.getMarks().add(cm);
				}
			}
			dao.updateBean(s);
		}
	}
	
	private void updateCourses(Student newStudent, Set<Course> oldCourses)
	{
		if (oldCourses != null)
		{
			// Delete student from old courses
			if (oldCourses.size() > 0)
			{
				System.out.println("Deleting old courses");
				for (Course c : oldCourses)
				{
					if (!newStudent.getEnrolled_courses().contains(c))
					{
						System.out.println("Processing old course " + c.getCourse_code());
						c.getStudents().remove(newStudent);
						cservice.update(c);
						updateStudentGrade(newStudent, c);
					}
				}
			}
			
			// Add student to new course
			for (Course c : newStudent.getEnrolled_courses())
			{
				if (oldCourses.size() == 0 || !oldCourses.contains(c))
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
