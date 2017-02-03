package hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mcit.kritth.bo.template.*;
import com.mcit.kritth.model.data.*;
import com.mcit.kritth.model.messenger.*;
import com.mcit.kritth.util.BeanUtil;

public class HibernateInsertScript
{
	public static void main(String[] args)
	{		
		ApplicationContextProvider.getApplicationContext();
		
		// Student admission
    	StudentAdmissionStatus sa = new StudentAdmissionStatus();
        sa.setStatus("Pending");
        sa.setDescription("testing");
        ApplicationContextProvider.getApplicationContext().getBean("studentAdmissionStatusService", StudentAdmissionStatusBO.class).insert(sa);
        
        // Department code
        DepartmentCode dc2 = new DepartmentCode();
        dc2.setDept_code("ARCH");
        dc2.setDept_name("Architecture");
        ApplicationContextProvider.getApplicationContext().getBean("departmentCodeService", DepartmentCodeBO.class).insert(dc2);
        
        DepartmentCode dc = new DepartmentCode();
        dc.setDept_code("COMP");
        dc.setDept_name("Computer Science");
        ApplicationContextProvider.getApplicationContext().getBean("departmentCodeService", DepartmentCodeBO.class).insert(dc);
        
        // Department
    	Department d = new Department();
    	d.setCode(dc);
    	d.setBudget(100000);
    	ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class).insert(d);
    	
    	Department d2 = new Department();
    	d2.setCode(dc2);
    	d2.setBudget(100000);
    	ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class).insert(d2);
    	
    	// Person
        Person p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        p.setEmail("test");
        p.setGender('F');
        ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class).insert(p);
        
        // Student
        Student s = new Student();
        s.setAdmissionStatus(sa);
        s.setCredit(10);
        s.setPerson(p);
        s.setDepartment(d);
        ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class).insert(s);
        
        // Employee
        Employee e = new Employee();
        e.setDepartment(d);
        e.setPerson(p);
        ApplicationContextProvider.getApplicationContext().getBean("employeeService", EmployeeBO.class).insert(e);
        
        // Update department
        d.setDean(e);
        d.getStudents().add(s);
        d.getEmployees().add(e);
        ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class).update(d);
        
        // User
        User u = new User();
        u.setUsername(ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class).getNewUsername(p));
        u.setPassword("pass");
        u.setPerson(p);
        ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class).insert(u);
        
        // Course
        Course c = new Course();
        c.setCourse_name("Introduction to Computer Science");
        c.setDepartment(d);
        c.setInstructor(e);
        c.getStudents().add(s);
        c.setCourse_code(BeanUtil.getCourseCode(c));
        c.setCapacity(100);
        ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class).insert(c);
        
        Course c2 = new Course();
        c2.setCourse_name("Introduction to Architecture");
        c2.setDepartment(d2);
        c2.setInstructor(e);
        c2.getStudents().add(s);
        c2.setCourse_code(BeanUtil.getCourseCode(c2));
        c2.setCapacity(20);
        ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class).insert(c2);
        
        Course c3 = new Course();
        c3.setCourse_name("Introduction to Algorithm");
        c3.setDepartment(d);
        c3.setInstructor(e);
        c3.setClass_level(2);
        c3.setCourse_number(10);
        c3.setCourse_code(BeanUtil.getCourseCode(c3));
        c3.setCapacity(30);
        ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class).insert(c3);
        
        // Update assigned course
        e.getAssigned_courses().add(c);
        e.getAssigned_courses().add(c2);
        e.getAssigned_courses().add(c3);
        ApplicationContextProvider.getApplicationContext().getBean("employeeService", EmployeeBO.class).update(e);
        
        // Course Work
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        
        CourseWork cw = new CourseWork();
        cw.setContribution(0.7);
        cw.setCoursework_description("test");
        cw.setCoursework_name("Assignment 1");
        cw.setMax_mark(30);
        cw.setCourse(c);
        try {
			cw.setDeadline(sdf.parse("31/12/2099-23:30"));
		} catch (ParseException e1) { }
        ApplicationContextProvider.getApplicationContext().getBean("courseWorkService", CourseWorkBO.class).insert(cw);
        
        CourseWork cw2 = new CourseWork();
        cw2.setContribution(0.1);
        cw2.setCoursework_description("test");
        cw2.setCoursework_name("Assignment 2");
        cw2.setMax_mark(30);
        cw2.setCourse(c);
        try {
			cw2.setDeadline(sdf.parse("31/12/2100-23:30"));
		} catch (ParseException e1) { }
        ApplicationContextProvider.getApplicationContext().getBean("courseWorkService", CourseWorkBO.class).insert(cw2);
        
        CourseWork cw3 = new CourseWork();
        cw3.setContribution(0.9);
        cw3.setCoursework_description("test");
        cw3.setCoursework_name("Assignment 1");
        cw3.setMax_mark(30);
        cw3.setCourse(c2);
        try {
			cw3.setDeadline(sdf.parse("31/12/2099-23:30"));
		} catch (ParseException e1) { }
        ApplicationContextProvider.getApplicationContext().getBean("courseWorkService", CourseWorkBO.class).insert(cw3);
        
        c.getCourse_works().add(cw);
        c.getCourse_works().add(cw2);
        ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class).update(c);
        
        c2.getCourse_works().add(cw3);
        ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class).update(c2);
        
        // Course Mark
        CourseMark cm = new CourseMark();
        cm.setMark(20);
        cm.setCoursework(cw);
        cm.setStudent(s);
        ApplicationContextProvider.getApplicationContext().getBean("courseMarkService", CourseMarkBO.class).insert(cm);
        
        CourseMark cm2 = new CourseMark();
        cm2.setMark(20);
        cm2.setCoursework(cw3);
        cm2.setStudent(s);
        ApplicationContextProvider.getApplicationContext().getBean("courseMarkService", CourseMarkBO.class).insert(cm2);
        
        // Student Grade
        StudentGrade sg = new StudentGrade();
        sg.getCourseMarks().add(cm);
        sg.setStudent(s);
        sg.setCourse(c);
        ApplicationContextProvider.getApplicationContext().getBean("studentGradeService", StudentGradeBO.class).insert(sg);
        
        StudentGrade sg2 = new StudentGrade();
        sg2.getCourseMarks().add(cm2);
        sg2.setStudent(s);
        sg2.setCourse(c2);
        ApplicationContextProvider.getApplicationContext().getBean("studentGradeService", StudentGradeBO.class).insert(sg2);
        
        s.getMarks().add(sg);
        s.getMarks().add(sg2);
        ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class).update(s);
        
        // Create second person and user to test messages
        Person p2 = new Person();
        p2.setFirstName("sub_admin");
        p2.setLastName("sub_admin");
        p2.setEmail("test");
        p2.setGender('M');
        p2.setID(2);
        ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class).insert(p2);
        
        User u2 = new User();
        u2.setUsername(ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class).getNewUsername(p2));
        u2.setPassword("test");
        u2.setPerson(p2);
        ApplicationContextProvider.getApplicationContext().getBean("userService", UserBO.class).insert(u2);
        
        // create few messages
        Personal pm1 = new Personal();
        pm1.setHeadline("Test Important 1");
        pm1.setImportant(true);
        pm1.setMessage("This is test message for important message");
        pm1.setReceiver(p);
        pm1.setSender(p);
        ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class).insert(pm1);
        
        Personal pm2 = new Personal();
        pm2.setHeadline("Test normal 1");
        pm2.setImportant(false);
        pm2.setMessage("This is test message for normal message 1");
        pm2.setReceiver(p);
        pm2.setSender(p2);
        ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class).insert(pm2);
        
        Personal pm3 = new Personal();
        pm3.setHeadline("Test normal 2");
        pm3.setImportant(false);
        pm3.setMessage("This is test message for normal message 2");
        pm3.setReceiver(p);
        pm3.setSender(p2);
        pm3.setTrash(true);
        ApplicationContextProvider.getApplicationContext().getBean("personalService", PersonalBO.class).insert(pm3);
        
        s.getEnrolled_courses().add(c);
        s.getEnrolled_courses().add(c2);
        ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class).update(s);
        
        System.exit(0);
	}
}