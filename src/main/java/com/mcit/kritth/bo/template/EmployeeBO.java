package com.mcit.kritth.bo.template;

import java.util.Set;

import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Employee;

public interface EmployeeBO extends BaseBO<Employee> {
	boolean updateAssignedCourse(Employee obj, Course objToAdd);
	boolean updateAssignedCourseList(Employee obj, Set<Course> objToAdd);
}