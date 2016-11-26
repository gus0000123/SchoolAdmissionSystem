/*
 * Made by: Mathieu Legault
 * Creation date: Jun 14, 2016
 */
package hibernate;

import beans.*;
import dao.*;
import util.BeanUtil;

/**
 *
 * @author Administrator
 */
public class TestHibernate {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
    	// Student admission
    	StudentAdmissionStatus sa = new StudentAdmissionStatus();
        sa.setStatus("Pending");
        sa.setDescription("");
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
        
        // Update dean
        d.setDean(e);
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
        CourseDAO.getInstance().insert(c);
    }
}
