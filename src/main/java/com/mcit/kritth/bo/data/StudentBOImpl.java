package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.dao.template.StudentDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentAdmissionStatus;
import com.mcit.kritth.model.data.StudentGrade;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Service("studentService")
@Transactional
public class StudentBOImpl implements StudentBO
{
	@Autowired
	private StudentDAO dao;
	
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
	public void delete(Student o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Student getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAllBeans(); }
	
	private void updateCourses(Student newStudent, Set<Course> oldCourses)
	{
		CourseBO cservice = ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class);
		
		if (oldCourses != null)
		{
			// Delete student from old courses
			for (Course c : oldCourses)
			{
				if (!newStudent.getEnrolled_courses().contains(c))
				{
					c.getStudents().remove(newStudent);
					cservice.update(c);
				}
			}
			
			// Add student to new course
			for (Course c : newStudent.getEnrolled_courses())
			{
				if (!oldCourses.contains(c))
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
