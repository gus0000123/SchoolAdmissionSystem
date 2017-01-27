package com.mcit.kritth.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestStudent implements TestBean
{
	private Student s;
	private int id;
	private String major;
	private String minor;
	private int credit;
	private int year;
	private Date start_date;
	private Department department;
	private StudentAdmissionStatus admission_status;
	private Person person;
	private Set<Course> enrolled_courses;
	private Set<StudentGrade> marks;
	
	@Before
	@Override
	public void init()
	{
		s = new Student();
		id = TestUtil.generateRandomNumber();
		major = TestUtil.generateRandomString();
		minor = TestUtil.generateRandomString();
		credit = TestUtil.generateRandomNumber();
		year = TestUtil.generateRandomNumber();
		start_date = new Date();
		department = new Department();
		admission_status = new StudentAdmissionStatus();
		person = new Person();
		person.setAddress(new Address());
		enrolled_courses = new HashSet<>();
		marks = new HashSet<>();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		s.setId(id);
		s.setMajor(major);
		s.setMinor(minor);
		s.setCredit(credit);
		s.setYear(year);
		s.setStartDate(start_date);
		s.setDepartment(department);
		s.setAdmissionStatus(admission_status);
		s.setPerson(person);
		s.setEnrolled_courses(enrolled_courses);
		s.setMarks(marks);
		
		assertNotNull(s);
		assertNotNull(s.getId());
		assertNotNull(s.getMajor());
		assertNotNull(s.getMinor());
		assertNotNull(s.getCredit());
		assertNotNull(s.getYear());
		assertNotNull(s.getStartDate());
		assertNotNull(s.getDepartment());
		assertNotNull(s.getAdmissionStatus());
		assertNotNull(s.getPerson());
		assertNotNull(s.getEnrolled_courses());
		assertNotNull(s.getMarks());
		assertNotNull(s.toString());
		
		s = new Student();
		
		s.getEnrolled_courses().add(new Course());
		s.getMarks().add(new StudentGrade());
		
		assertNotNull(s.getEnrolled_courses());
		assertNotNull(s.getMarks());
	}

	@Test
	public void testOverride()
	{
		assertEquals(s, s);
		assertFalse(s.equals("String"));
		Student student2 = new Student();
		student2.setId(-1);
		assertFalse(s.equals(student2));
	}
}
