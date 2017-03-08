package com.mcit.kritth.model.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestCourseMark implements TestBean
{
	private CourseMark course_mark;
	private int id;
	private int mark;
	private CourseWork coursework;
	private Student student;
	
	@Before
	@Override
	public void init()
	{
		course_mark = new CourseMark();
		id = TestUtil.generateRandomNumber(99);
		mark = TestUtil.generateRandomNumber(99);
		coursework = new CourseWork();
		student = new Student();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		course_mark.setCoursemark_id(id);
		course_mark.setMark(mark);
		course_mark.setCoursework(coursework);
		course_mark.setStudent(student);
		
		assertNotNull(course_mark);
		assertNotNull(course_mark.getCoursemark_id());
		assertNotNull(course_mark.getMark());
		assertNotNull(course_mark.getCoursework());
		assertNotNull(course_mark.getStudent());
		assertNotNull(course_mark.toString());
	}

	@Test
	public void testOverride()
	{
		CourseMark cm2 = new CourseMark();
		cm2.setCoursemark_id(-1);
		cm2.setCoursework(new CourseWork());
		cm2.setStudent(new Student());
		cm2.setMark(-1);
		Address address3 = new Address();
		address3.setCity("c");
		address3.setCountry("");
		address3.setPostal("");
		address3.setState("");
		address3.setStreetAddress("");
		assertTrue(course_mark.equals(course_mark));
		assertFalse(course_mark.equals(cm2));
		assertFalse(course_mark.equals(address3));
	}
}