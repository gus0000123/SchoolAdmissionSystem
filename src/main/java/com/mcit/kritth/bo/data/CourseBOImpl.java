package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.dao.template.CourseDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;

@Service("courseService")
@Transactional
public class CourseBOImpl implements CourseBO
{
	@Autowired
	private CourseDAO dao;
	
	@Autowired
	private EmployeeBO eservice;
	
	@Autowired
	private StudentBO sservice;
	
	@Autowired
	private CourseWorkBO cwservice;
	
	@Autowired
	private CourseMarkBO cmservice;
	
	@Override
	public void insert(Course o) { dao.insertBean(o); }

	@Override
	public void update(Course o)
	{
		Course c = getById(o.getCourse_code());
		
		Set<Student> oldStudents = c.getStudents();
		Set<CourseWork> oldCourseWorks = c.getCourse_works();
		Employee oldInstructor = c.getInstructor();
		
		c.setClass_level(o.getClass_level());
		c.setCourse_number(o.getCourse_number());
		c.setSection(o.getSection());
		c.setDepartment(o.getDepartment());
		
		c.getCourse_code();
		
		//c.setCourse_works(o.getCourse_works());
		c.setInstructor(o.getInstructor());
		c.setStudents(o.getStudents());
		c.setCourse_works(o.getCourse_works());
		
		dao.updateBean(c);
		
		// Check if course works has changed
		updateCourseWorks(c, oldCourseWorks);
		
		// Check if student changed
		updateStudents(c, oldStudents);
		
		// Update instructor
		updateInstructor(c, oldInstructor, o.getInstructor());
	}

	@Override
	public void delete(Course o)
	{
		// Clear students
		updateStudents(o, null);
		
		// Clear Courseworks
		for (CourseWork cw : o.getCourse_works())
		{
			try { cwservice.delete(cw); }
			catch (Exception e) { e.printStackTrace(); }
		}
		
		// Remove instructor
		if (o.getInstructor() != null && o.getInstructor().getAssigned_courses().contains(o))
		{
			o.getInstructor().getAssigned_courses().remove(o);
			eservice.update(o.getInstructor());
		}
		
		dao.removeBeanByPrimaryKey(o.getCourse_code());
	}

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public Course getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Course> getAll() { return dao.getAllBeans(); }
	
	private void updateCourseWorks(Course c, Set<CourseWork> old_course_works)
	{
		// Old coursework
		for (CourseWork cw : old_course_works)
		{
			if (!c.getCourse_works().contains(cw))
			{
				try { cwservice.delete(cw); }
				catch (Exception e) { e.printStackTrace(); }
			}
		}
	}

	private void updateStudentCourseMark(Course c, Student s)
	{
		if (s.getEnrolled_courses().contains(c))
		{
			for (CourseWork cw : c.getCourse_works())
			{
				boolean found = false;
				for (CourseMark cm : s.getMarks())
				{
					if (cm.getCoursework().equals(cw))
					{
						found = true;
						break;
					}
				}
				
				if (!found)
				{
					CourseMark cm = new CourseMark();
					cm.setCoursework(cw);
					cm.setMark(0);
					cm.setStudent(s);
					cmservice.insert(cm);
					
					s.getMarks().add(cm);
					sservice.update(s);
				}
			}
		}
	}
	
	private void updateStudents(Course newCourse, Set<Student> oldStudents)
	{		
		if (oldStudents != null)
		{
			// Delete course from old student
			for (Student s : oldStudents)
			{
				if (!newCourse.getStudents().contains(s))
				{
					s.getEnrolled_courses().remove(newCourse);
					sservice.update(s);
				}
			}
			
			// Add course to new student
			for (Student s : newCourse.getStudents())
			{
				if (!oldStudents.contains(s))
				{
					s.getEnrolled_courses().add(newCourse);
					sservice.update(s);
				}
				
				updateStudentCourseMark(newCourse, s);
			}
		}
		else
		{
			for (Student s : newCourse.getStudents())
			{
				s.getEnrolled_courses().remove(newCourse);
				sservice.update(s);
			}
		}
	}
	
	private void updateInstructor(Course c, Employee oldInstructor, Employee newInstructor)
	{
		if (newInstructor != null && !oldInstructor.equals(newInstructor))
		{
			oldInstructor.getAssigned_courses().remove(c);
			eservice.update(oldInstructor);
			newInstructor.getAssigned_courses().add(c);
			eservice.update(newInstructor);
		}
	}
}