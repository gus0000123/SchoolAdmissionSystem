package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

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
		course_mark.setId(id);
		course_mark.setMark(mark);
		course_mark.setCoursework(coursework);
		course_mark.setStudent(student);
		
		assertNotNull(course_mark);
		assertNotNull(course_mark.getId());
		assertNotNull(course_mark.getMark());
		assertNotNull(course_mark.getCoursework());
		assertNotNull(course_mark.getStudent());
		assertNotNull(course_mark.toString());
	}

}