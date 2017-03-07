package com.mcit.kritth.controller.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.*;
import com.mcit.kritth.model.data.*;
import com.mcit.kritth.util.BeanUtil;

@Controller
public class HibernateInsertScript
{
	@Autowired
	private  StudentAdmissionStatusBO saservice;
	
	@Autowired
	private  DepartmentCodeBO dcservice;
	
	@Autowired
	private  DepartmentBO dservice;
	
	@Autowired
	private  PersonBO pservice;
	
	@Autowired
	private  StudentBO sservice;
	
	@Autowired
	private  EmployeeBO eservice;
	
	@Autowired
	private  UserBO uservice;
	
	@Autowired
	private  CourseBO cservice;
	
	@Autowired
	private  CourseWorkBO cwservice;
	
	@Autowired
	private  CourseMarkBO cmservice;
	
	@RequestMapping(value = "/debug/insert", method= {RequestMethod.GET})
	public ModelAndView debugScript(String[] args)
	{		
		// Student admission
    	StudentAdmissionStatus sa = new StudentAdmissionStatus();
        sa.setStatus("Pending");
        sa.setDescription("testing");
        saservice.insert(sa);
        
        StudentAdmissionStatus sa2 = new StudentAdmissionStatus();
        sa2.setStatus("Admitted");
        sa2.setDescription("testing");
        saservice.insert(sa2);
        
        // Department code
        DepartmentCode dc2 = new DepartmentCode();
        dc2.setDept_code("ARCH");
        dc2.setDept_name("Architecture");
        dcservice.insert(dc2);
        
        DepartmentCode dc = new DepartmentCode();
        dc.setDept_code("COMP");
        dc.setDept_name("Computer Science");
        dcservice.insert(dc);
        
        // Department
    	Department d = new Department();
    	d.setDept_code(dc);
    	d.setBudget(100000);
    	dservice.insert(d);
    	
    	Department d2 = new Department();
    	d2.setDept_code(dc2);
    	d2.setBudget(100000);
    	dservice.insert(d2);
    	
    	// Person
        Person p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        p.setEmail("test");
        p.setGender('F');
        pservice.insert(p);
        
        // Student
        Student s = new Student();
        s.setAdmission_status(sa);
        s.setCredit(10);
        s.setPerson(p);
        s.setDepartment(d);
        sservice.insert(s);
        
        // Employee
        Employee e = new Employee();
        e.setDepartment(d);
        e.setPerson(p);
        eservice.insert(e);
        
        // Update department
        d.setDean(e);
        dservice.update(d);
        
        // User
        User u = new User();
        u.setUsername(uservice.getNewUsername(p));
        u.setPassword("pass");
        u.setPerson(p);
        uservice.insert(u);
        
        // Course
        Course c = new Course();
        c.setCourse_name("Introduction to Computer Science");
        c.setDepartment(d);
        c.setInstructor(e);
        c.getStudents().add(s);
        c.setCourse_code(BeanUtil.getCourseCode(c));
        c.setCapacity(100);
        cservice.insert(c);
        
        Course c2 = new Course();
        c2.setCourse_name("Introduction to Architecture");
        c2.setDepartment(d2);
        c2.setInstructor(e);
        c2.getStudents().add(s);
        c2.setCourse_code(BeanUtil.getCourseCode(c2));
        c2.setCapacity(20);
        cservice.insert(c2);
        
        Course c3 = new Course();
        c3.setCourse_name("Introduction to Algorithm");
        c3.setDepartment(d);
        c3.setInstructor(e);
        c3.setClass_level(2);
        c3.setCourse_number(10);
        c3.setCourse_code(BeanUtil.getCourseCode(c3));
        c3.setCapacity(30);
        cservice.insert(c3);
        
        // Update assigned course
        e.getAssigned_courses().add(c);
        e.getAssigned_courses().add(c2);
        e.getAssigned_courses().add(c3);
        eservice.update(e);
        
        // Course Work
        CourseWork cw = new CourseWork();
        cw.setContribution(0.7);
        cw.setCoursework_description("test");
        cw.setCoursework_name("Assignment 1");
        cw.setMax_mark(30);
        cw.setCourse(c);
        cwservice.insert(cw);
        cw = cwservice.getById(1);
        
        CourseWork cw2 = new CourseWork();
        cw2.setContribution(0.1);
        cw2.setCoursework_description("test");
        cw2.setCoursework_name("Assignment 2");
        cw2.setMax_mark(30);
        cw2.setCourse(c);
        cwservice.insert(cw2);
        cw2 = cwservice.getById(2);
        
        CourseWork cw3 = new CourseWork();
        cw3.setContribution(0.9);
        cw3.setCoursework_description("test");
        cw3.setCoursework_name("Assignment 1");
        cw3.setMax_mark(30);
        cw3.setCourse(c2);
        cwservice.insert(cw3);
        cw3 = cwservice.getById(3);
        
        s.getEnrolled_courses().add(c);
        sservice.update(s);
        
        s.getEnrolled_courses().add(c2);
        sservice.update(s);
        
        // Create second person and user to test messages
        Person p2 = new Person();
        p2.setFirstName("sub_admin");
        p2.setLastName("sub_admin");
        p2.setEmail("test");
        p2.setGender('M');
        p2.setID(2);
        pservice.insert(p2);
        
        User u2 = new User();
        u2.setUsername(uservice.getNewUsername(p2));
        u2.setPassword("test");
        u2.setPerson(p2);
        uservice.insert(u2);
        
        ModelAndView model = new ModelAndView("redirect:/");
        
        return model;
	}
}