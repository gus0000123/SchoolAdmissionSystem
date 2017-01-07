package com.mcit.kritth.bo.template;

import java.util.Set;

import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentAdmissionStatus;
import com.mcit.kritth.model.data.StudentGrade;

public interface StudentBO extends BaseBO<Student> {
	boolean updateStudentGrade(Student obj, StudentGrade objToAdd);
	boolean updateStudentGradeList(Student obj, Set<StudentGrade> objToAdd);
	boolean updateCourse(Student obj, Course objToAdd);
	boolean updateCourseList(Student obj, Set<Course> objToAdd);
	boolean updatePerson(Student obj, Person objToAdd);
	boolean updateStatus(Student obj, StudentAdmissionStatus objToAdd);
	boolean updateDepartment(Student obj, Department objToAdd);
}
