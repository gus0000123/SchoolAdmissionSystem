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
	public void update(Student o) { dao.updateBean(o); }

	@Override
	public void delete(Student o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Student getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAllBeans(); }

	/************************ADDING DEPENDENCY********************************/

	@Override
	public boolean updateStudentGrade(Student obj, StudentGrade objToAdd)
	{
		return false;
	}
	
	@Override
	public boolean updateStudentGradeList(Student obj, Set<StudentGrade> objToAdd)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean updateCourse(Student obj, Course course)
	{
		CourseBO cservice = ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class);
		
		if (course != null)
		{
			if (obj.getEnrolled_courses().contains(course)) { obj.getEnrolled_courses().remove(course);	}
			else { obj.getEnrolled_courses().add(course); }
			
			if (!course.getStudents().contains(obj))
			{
				cservice.updateStudent(course, obj);
			}
			return true;
		}
		else
		{
			return updateCourseList(obj, null);
		}
	}

	@Override
	public boolean updateCourseList(Student obj, Set<Course> objToAdd)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePerson(Student obj, Person objToAdd)
	{
		// Person does not need to update dependency
		obj.setPerson(objToAdd);
		update(obj);
		return true;
	}

	@Override
	public boolean updateStatus(Student obj, StudentAdmissionStatus objToAdd)
	{
		// Status does not need to update dependency
		obj.setAdmissionStatus(objToAdd);
		update(obj);
		return true;
	}

	@Override
	public boolean updateDepartment(Student obj, Department objToAdd)
	{
		// Department does not need to update dependency
		obj.setDepartment(objToAdd);
		update(obj);
		return true;
	}
}
