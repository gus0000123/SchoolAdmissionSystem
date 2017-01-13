package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.dao.template.CourseDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Service("courseService")
@Transactional
public class CourseBOImpl implements CourseBO
{
	@Autowired
	private CourseDAO dao;
	
	@Override
	public void insert(Course o) { dao.insertBean(o); }

	@Override
	public void update(Course o)
	{
		Course c = getById(o.getCourse_code());
		
		Set<Student> oldStudents = c.getStudents();
		
		c.setClass_level(o.getClass_level());
		c.setCourse_number(o.getCourse_number());
		c.setSection(o.getSection());
		c.setDepartment(o.getDepartment());
		
		c.getCourse_code();
		
		c.setInstructor(o.getInstructor());
		c.setStudents(o.getStudents());
		
		dao.updateBean(c);
		
		// Check if student changed
		updateStudents(c, oldStudents);
	}

	@Override
	public void delete(Course o)
	{
		// Clear students
		updateStudents(o, null);
		/*
		updateDepartment(o, null);
		updateInstructor(o, null);
		updateTA(o, null);
		updateStudent(o, null);
		updatePrerequisite(o, null);
		*/
		// does not need coursework as it is cascade.all
		dao.removeBeanByPrimaryKey(o.getCourse_code());
	}
	
	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Course getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Course> getAll() { return dao.getAllBeans(); }

	private void updateStudents(Course newCourse, Set<Student> oldStudents)
	{
		StudentBO sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
		
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
}