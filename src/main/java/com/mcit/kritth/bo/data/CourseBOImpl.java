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
	public void update(Course o) { dao.updateBean(o); }

	@Override
	public void delete(Course o)
	{
		updateDepartment(o, null);
		updateInstructor(o, null);
		updateTA(o, null);
		updateStudent(o, null);
		updatePrerequisite(o, null);
		// does not need coursework as it is cascade.all
		dao.removeBeanByPrimaryKey(o.getCourse_code());
	}
	
	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Course getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Course> getAll() { return dao.getAllBeans(); }
	
	/************************ADDING DEPENDENCY********************************/
	
	@Override
	public boolean updateDepartment(Course obj, Department objToAdd)
	{
		// Department does not need to update dependency
		obj.setDepartment(objToAdd);
		obj.getCourse_code(); // update course code
		update(obj);
		return true;
	}
	
	@Override
	public boolean updateInstructor(Course obj, Employee objToAdd)
	{
		EmployeeBO eservice = ApplicationContextProvider.getApplicationContext().getBean(EmployeeBO.class);
		
		if (objToAdd != null)
			eservice.updateAssignedCourse(objToAdd, obj);
		if (obj.getInstructor() != null)
			eservice.updateAssignedCourse(obj.getInstructor(), obj); // Remove reference in old instructor
		obj.setInstructor(objToAdd);
		update(obj);
		return true;
	}
	
	// TODO: when prerequisite is implemented
	@Override
	public boolean updatePrerequisite(Course obj, Set<Course> objToAdd)
	{
		return true;
	}
	
	@Override
	public boolean updateStudent(Course obj, Student objToAdd)
	{
		StudentBO sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
		
		if (objToAdd != null)
		{
			if (obj.getStudents().contains(objToAdd))
				obj.getStudents().remove(objToAdd);
			else
				obj.getStudents().add(objToAdd);
			
			update(obj);
			if (objToAdd.getEnrolled_courses().contains(obj))
				sservice.updateCourse(objToAdd, obj);
			
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateStudentList(Course obj, Set<Student> objToAdd)
	{
		StudentBO sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
		
		if (objToAdd != null)
		{
			Set<Student> setToRemove = new HashSet<Student>();
			
			// Check add process
			for (Student s : objToAdd)
			{
				if (!obj.getStudents().contains(s))
				{
					obj.getStudents().add(s);
					sservice.updateCourse(s, obj);
				}
			}
			
			// Check remove process
			for (Student s : obj.getStudents())
			{
				if (!objToAdd.contains(s))
				{ 
					setToRemove.add(s);
				}
				
			}
			
			// Update
			for (Student s : setToRemove)
			{
				obj.getStudents().remove(s);
				sservice.updateCourse(s, obj);
			}
			update(obj);
		}
		else
		{
			Set<Student> students = new HashSet<>();
			students.addAll(obj.getStudents());
			obj.getStudents().clear();
			for (Student s : students)
			{
				sservice.updateCourse(s, obj);
			}
		}
		return true;
	}
	
	@Override
	public boolean updateTA(Course obj, Set<Employee> objToAdd)
	{
		EmployeeBO eservice = ApplicationContextProvider.getApplicationContext().getBean(EmployeeBO.class);
		
		if (objToAdd != null)
		{
			Set<Employee> setToRemove = new HashSet<>();
			
			// Check add process
			for (Employee e : objToAdd)
			{
				// to avoid infinite loop
				if (!obj.getTa().contains(e))
				{
					obj.getTa().add(e);
					eservice.updateAssignedCourse(e, obj);
				}
			}
			
			// Check remove process
			for (Employee e : obj.getTa())
			{
				if (!objToAdd.contains(e))
				{
					setToRemove.add(e);
				}
			}
			
			// Update
			for (Employee e : setToRemove)
			{
				obj.getTa().remove(e);
				eservice.updateAssignedCourse(e, obj);
			}
			update(obj);
		}
		else
		{
			Set<Employee> ta = new HashSet<>();
			ta.addAll(obj.getTa());
			obj.getStudents().clear();
			for (Employee e : ta)
			{
				eservice.updateAssignedCourse(e, obj);
			}
		}
		
		return true;
	}
	
	@Override
	public boolean updateCourseWork(Course obj, CourseWork objToAdd)
	{
		CourseWorkBO cwservice = ApplicationContextProvider.getApplicationContext().getBean(CourseWorkBO.class);
		
		if (objToAdd != null)
		{
			if (obj.getCourse_works().contains(objToAdd))
			{
				if (objToAdd.getCourse().equals(obj)) cwservice.updateCourse(objToAdd, obj);
				obj.getCourse_works().remove(objToAdd);
				update(obj);
				return true;
			}
		}
		
		return false;
		
	}
	
	@Override
	public boolean updateCourseWorkList(Course obj, Set<CourseWork> objToAdd)
	{
		CourseWorkBO cwservice = ApplicationContextProvider.getApplicationContext().getBean(CourseWorkBO.class);
		
		if (objToAdd != null)
		{
			Set<CourseWork> setToRemove = new HashSet<>();
		
			// Check add process
		
			for (CourseWork e : objToAdd)
			{
				// to avoid infinite loop
				if (!obj.getCourse_works().contains(e))
				{
					obj.getCourse_works().add(e);
					cwservice.updateCourse(e, obj);
				}
			}
		
			
			// Check remove process
			for (CourseWork e : obj.getCourse_works())
			{
				if (!objToAdd.contains(e))
				{
					setToRemove.add(e);
				}
			}
			
			// Update
			for (CourseWork e : setToRemove)
			{
				obj.getCourse_works().remove(e);
				cwservice.delete(e);
			}
			update(obj);
		}
		else
		{
			Set<CourseWork> courseworks = new HashSet<>();
			courseworks.addAll(obj.getCourse_works());
			obj.getCourse_works().clear();
			for (CourseWork e : courseworks)
			{
				cwservice.delete(e);
			}
		}
		
		return true;
	}
}
