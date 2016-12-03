package hibernate;

import bean.data.*;
import bean.messenger.Personal;
import dao.data.*;
import dao.messenger.PersonalDAO;
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
        
        // Department code
        DepartmentCode dc = new DepartmentCode();
        dc.setDept_code("COMP");
        dc.setDept_name("Computer Science");
        DepartmentCodeDAO.getInstance().insert(dc);
        
    	// Department
    	Department d = new Department();
    	d.setCode(dc);
    	d.setBudget(100000);
    	DepartmentDAO.getInstance().insert(d);
    	
    	// Person
        Person p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        p.setEmail("test");
        p.setGender('F');
        p.setDepartment(d);
        PersonDAO.getInstance().insert(p);
        
        // Student
        Student s = new Student();
        s.setAdmissionStatus(sa);
        s.setCredit(10);
        s.setMajor("COMP");
        s.setPerson(p);
        StudentDAO.getInstance().insert(s);
        
        // Employee
        Employee e = new Employee();
        e.setPerson(p);
        EmployeeDAO.getInstance().insert(e);
        
        // Update department
        d.setDean(e);
        d.getStudents().add(s);
        d.getEmployees().add(e);
        DepartmentDAO.getInstance().update(d);
        
        // User
        User u = new User();
        u.setUser(BeanUtil.generateUserName(p));
        u.setPassword("pass");
        u.setPerson(p);
        UserDAO.getInstance().insert(u);
        
        // Course
        Course c = new Course();
        c.setCourse_name("Introduction to Computer Science");
        c.setDepartment(d);
        c.setInstructor(e);
        c.getStudents().add(s);
        c.setCourse_code(BeanUtil.getCourseCode(c));
        CourseDAO.getInstance().insert(c);
        
        // Update assigned course
        e.getAssigned_courses().add(c);
        EmployeeDAO.getInstance().update(e);
        
        // Course Work
        CourseWork cw = new CourseWork();
        cw.setContribution(0.2);
        cw.setCoursework_description("test");
        cw.setCoursework_name("Assignment 1");
        cw.setMax_mark(30);
        cw.setCourse(c);
        CourseWorkDAO.getInstance().insert(cw);
        
        // Course Mark
        CourseMark cm = new CourseMark();
        cm.setMark(20);
        cm.setCoursework(cw);
        cm.setStudent(s);
        CourseMarkDAO.getInstance().insert(cm);
        
        // Student Grade
        StudentGrade sg = new StudentGrade();
        sg.getCourseMarks().add(cm);
        sg.setStudent(s);
        StudentGradeDAO.getInstance().insert(sg);
        
        // Create second person and user to test messages
        Person p2 = new Person();
        p2.setFirstName("sub_admin");
        p2.setLastName("sub_admin");
        p2.setEmail("test");
        p2.setGender('M');
        p2.setDepartment(d);
        p2.setID(2);
        PersonDAO.getInstance().insert(p2);
        
        User u2 = new User();
        u2.setUser(BeanUtil.generateUserName(p2));
        u2.setPassword("test");
        u2.setPerson(p2);
        UserDAO.getInstance().insert(u2);
        
        // create few messages
        Personal pm1 = new Personal();
        pm1.setHeadline("Test Important 1");
        pm1.setImportant(true);
        pm1.setMessage("This is test message for important message");
        pm1.setReceiver(p);
        pm1.setSender(p);
        PersonalDAO.getInstance().insert(pm1);
        
        Personal pm2 = new Personal();
        pm2.setHeadline("Test normal 1");
        pm2.setImportant(false);
        pm2.setMessage("This is test message for normal message 1");
        pm2.setReceiver(p);
        pm2.setSender(p2);
        PersonalDAO.getInstance().insert(pm2);
        
        Personal pm3 = new Personal();
        pm3.setHeadline("Test normal 2");
        pm3.setImportant(false);
        pm3.setMessage("This is test message for normal message 2");
        pm3.setReceiver(p);
        pm3.setSender(p2);
        PersonalDAO.getInstance().insert(pm3);
        
        System.exit(0);
	}
}