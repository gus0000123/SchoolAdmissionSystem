package com.mcit.kritth.bo.template;

import java.util.Set;

import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;

public interface CourseBO extends BaseBO<Course> {
	boolean updateDepartment(Course obj, Department objToAdd);
	boolean updateInstructor(Course obj, Employee objToAdd);
	boolean updatePrerequisite(Course obj, Set<Course> objToAdd);
	boolean updateStudent(Course obj, Student objToAdd);
	boolean updateStudentList(Course obj, Set<Student> objToAdd);
	boolean updateTA(Course obj, Set<Employee> objToAdd);
	boolean updateCourseWork(Course obj, CourseWork objToAdd);
	boolean updateCourseWorkList(Course obj, Set<CourseWork> objToAdd);
}
