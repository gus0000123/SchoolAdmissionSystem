package hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.*;
import dao.*;
import util.BeanUtil;

public class TestHibernate
{
	private Course c;
	private CourseMark cm;
	private CourseWork cw;
	private Department d;
	private DepartmentCode dc;
	private Employee e;
	private Person p;
	private Student s;
	private StudentAdmissionStatus sa;
	private StudentGrade sg;
	private User u;
	
	@Before
	public void insertAll()
	{
		HibernateUtil.getSessionFactory();
		
		// Student admission
    	sa = new StudentAdmissionStatus();
        sa.setStatus("Pending");
        sa.setDescription("testing");
        StudentAdmissionStatusDAO.getInstance().insert(sa);
        
        // Department code
        dc = new DepartmentCode();
        dc.setDept_code("COMP");
        dc.setDept_name("Computer Science");
        DepartmentCodeDAO.getInstance().insert(dc);
        
    	// Department
    	d = new Department();
    	d.setCode(dc);
    	d.setBudget(100000);
    	DepartmentDAO.getInstance().insert(d);
    	
    	// Person
        p = new Person();
        p.setFirstName("admin");
        p.setLastName("admin");
        p.setEmail("test");
        p.setGender('F');
        p.setDepartment(d);
        PersonDAO.getInstance().insert(p);
        
        // Student
        s = new Student();
        s.setAdmissionStatus(sa);
        s.setCredit(10);
        s.setMajor("COMP");
        s.setPerson(p);
        StudentDAO.getInstance().insert(s);
        
        // Update student;
        sa.getStudent().add(s);
        StudentAdmissionStatusDAO.getInstance().update(sa);
        
        // Employee
        e = new Employee();
        e.setPerson(p);
        EmployeeDAO.getInstance().insert(e);
        
        // Update department
        d.setDean(e);
        d.getStudents().add(s);
        d.getEmployees().add(e);
        DepartmentDAO.getInstance().update(d);
        
        // User
        u = new User();
        u.setUser(BeanUtil.generateUserName(p));
        u.setPassword("pass");
        u.setPerson(p);
        UserDAO.getInstance().insert(u);
        
        // Course
        c = new Course();
        c.setCourse_name("Introduction to Computer Science");
        c.setDepartment(d);
        c.setInstructor(e);
        c.getStudents().add(s);
        CourseDAO.getInstance().insert(c);
        
        // Update assigned course
        e.getAssigned_courses().add(c);
        EmployeeDAO.getInstance().update(e);
        
        // Course Work
        cw = new CourseWork();
        cw.setContribution(0.2);
        cw.setCoursework_description("test");
        cw.setCoursework_name("Assignment 1");
        cw.setMax_mark(30);
        cw.setCourse(c);
        CourseWorkDAO.getInstance().insert(cw);
        
        // Course Mark
        cm = new CourseMark();
        cm.setMark(20);
        cm.setCoursework(cw);
        cm.setStudent(s);
        CourseMarkDAO.getInstance().insert(cm);
        
        // Student Grade
        sg = new StudentGrade();
        sg.getCourseMarks().add(cm);
        sg.setStudent(s);
        StudentGradeDAO.getInstance().insert(sg);
	}
	
	
    /**
     * @param args the command line arguments
     */
	@Test
    public void testAssociation()
    {
    	
    }
	
	@After
	public void closeTest()
	{
		HibernateUtil.getSessionFactory().close();
	}
}
