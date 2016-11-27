package hibernate;

import bean.data.*;
import dao.data.*;
import dao.messenger.PersonDAO;
import util.BeanUtil;
import util.HibernateUtil;

public class HibernateInsertScript
{
	public static void main(String[] args)
	{
		HibernateUtil.getSessionFactory();
		
		// Student admission
    	StudentAdmissionStatus sa = new StudentAdmissionStatus();
        sa.setStatus("Pending");
        sa.setDescription("testing");
        StudentAdmissionStatusDAO.getInstance().insert(sa);
        sa = StudentAdmissionStatusDAO.getInstance().getByPrimaryKey(1);
        
        // Department code
        DepartmentCode dc = new DepartmentCode();
        dc.setDept_code("COMP");
        dc.setDept_name("Computer Science");
        DepartmentCodeDAO.getInstance().insert(dc);
        dc = DepartmentCodeDAO.getInstance().getByPrimaryKey(1);
        
    	// Department
    	Department d = new Department();
    	d.setCode(dc);
    	d.setBudget(100000);
    	DepartmentDAO.getInstance().insert(d);
    	d = DepartmentDAO.getInstance().getByPrimaryKey(1);
    	
    	// Person
        Person p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        p.setEmail("test");
        p.setGender('F');
        p.setDepartment(d);
        PersonDAO.getInstance().insert(p);
        p = PersonDAO.getInstance().getByPrimaryKey(1);
        
        // Student
        Student s = new Student();
        s.setAdmissionStatus(sa);
        s.setCredit(10);
        s.setMajor("COMP");
        s.setPerson(p);
        StudentDAO.getInstance().insert(s);
        s = StudentDAO.getInstance().getByPrimaryKey(2);
        
        // Employee
        Employee e = new Employee();
        e.setPerson(p);
        EmployeeDAO.getInstance().insert(e);
        e = EmployeeDAO.getInstance().getByPrimaryKey(3);
        
        // Update department
        d.setDean(e);
        d.getStudents().add(s);
        d.getEmployees().add(e);
        DepartmentDAO.getInstance().update(d);
        d = DepartmentDAO.getInstance().getByPrimaryKey(1);
        
        // User
        User u = new User();
        u.setUser(BeanUtil.generateUserName(p));
        u.setPassword("pass");
        u.setPerson(p);
        UserDAO.getInstance().insert(u);
        u = UserDAO.getInstance().getByPrimaryKey(1);
        
        // Course
        Course c = new Course();
        c.setCourse_name("Introduction to Computer Science");
        c.setDepartment(d);
        c.setInstructor(e);
        c.getStudents().add(s);
        c.setCourse_code(BeanUtil.getCourseCode(c));
        CourseDAO.getInstance().insert(c);
        c = CourseDAO.getInstance().getByPrimaryKey(1);
        
        // Update assigned course
        e.getAssigned_courses().add(c);
        EmployeeDAO.getInstance().update(e);
        e = EmployeeDAO.getInstance().getByPrimaryKey(3);
        
        // Course Work
        CourseWork cw = new CourseWork();
        cw.setContribution(0.2);
        cw.setCoursework_description("test");
        cw.setCoursework_name("Assignment 1");
        cw.setMax_mark(30);
        cw.setCourse(c);
        CourseWorkDAO.getInstance().insert(cw);
        cw = CourseWorkDAO.getInstance().getByPrimaryKey(1);
        
        // Course Mark
        CourseMark cm = new CourseMark();
        cm.setMark(20);
        cm.setCoursework(cw);
        cm.setStudent(s);
        CourseMarkDAO.getInstance().insert(cm);
        cm = CourseMarkDAO.getInstance().getByPrimaryKey(1);
        
        // Student Grade
        StudentGrade sg = new StudentGrade();
        sg.getCourseMarks().add(cm);
        sg.setStudent(s);
        StudentGradeDAO.getInstance().insert(sg);
        sg = StudentGradeDAO.getInstance().getByPrimaryKey(1);
	}
}
