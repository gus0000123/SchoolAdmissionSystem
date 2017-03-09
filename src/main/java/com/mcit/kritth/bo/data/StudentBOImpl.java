package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.mcit.kritth.model.data.Student;

/**
 * Student Service layer
 * @author Kritth
 *
 */
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
		
		dao.updateBean(s);
		
		// Check if course changed
		updateCourses(s, oldCourses);
		
		int coursework_amount = 0;
		for (Course c : s.getEnrolled_courses()) { coursework_amount += c.getCourse_works().size(); }
		if (coursework_amount > 0 && o.getMarks().size() > 0) { s.setMarks(o.getMarks()); }
		if (coursework_amount == 0 && o.getMarks().size() == 0) { s.setMarks(o.getMarks()); }
		
		dao.updateBean(s);
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
		
		// Delete course marks
		for (CourseMark cm : o.getMarks())
		{
			try { cmservice.delete(cm); }
			catch (Exception e) { e.printStackTrace(); }
		}
		
		dao.removeBeanByPrimaryKey(o.getId());
	}

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public Student getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAllBeans(); }
	
	/**
	 * Bidirectional update for student and courses
	 * @param newStudent
	 * @param oldCourses
	 */
	private void updateCourses(Student newStudent, Set<Course> oldCourses)
	{
		if (oldCourses != null)
		{
			// Delete student from old courses
			if (oldCourses.size() > 0)
			{
				for (Course c : oldCourses)
				{
					if (!newStudent.getEnrolled_courses().contains(c))
					{
						c.getStudents().remove(newStudent);
						cservice.update(c);
						
						List<CourseMark> CourseMarkToRemove = new ArrayList<>();
						for (CourseMark cm : newStudent.getMarks())
						{
							if (cm.getCoursework().getCourse().equals(c))
							{
								try
								{
									cmservice.delete(cm);
									CourseMarkToRemove.add(cm);
								}
								catch (Exception e) { e.printStackTrace(); }
							}
						}
						
						for (CourseMark cm : CourseMarkToRemove)
						{
							newStudent.getMarks().remove(cm);
						}
						dao.updateBean(newStudent);
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
