package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestCourseWork implements TestBean
{
	private CourseWork course_work;
	private int coursework_id;
	private String coursework_name;
	private String coursework_description;
	private double contribution;
	private int max_mark;
	private Date creation_date;
	private Date deadline;
	private Course course;
	
	@Before
	@Override
	public void init()
	{
		course_work = new CourseWork();
		coursework_id = TestUtil.generateRandomNumber();
		coursework_name = TestUtil.generateRandomString();
		coursework_description = TestUtil.generateRandomString();
		contribution = TestUtil.generateRandomNumber();
		max_mark = TestUtil.generateRandomNumber();
		creation_date = deadline = new Date();
		course = new Course();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		course_work.setCoursework_id(coursework_id);
		course_work.setCoursework_name(coursework_name);
		course_work.setCoursework_description(coursework_description);
		course_work.setContribution(contribution);
		course_work.setMax_mark(max_mark);
		course_work.setCreation_date(creation_date);
		course_work.setDeadline(deadline);
		course_work.setCourse(course);
		
		assertNotNull(course_work);
		assertNotNull(course_work.getCoursework_id());
		assertNotNull(course_work.getCoursework_name());
		assertNotNull(course_work.getCoursework_description());
		assertNotNull(course_work.getContribution());
		assertNotNull(course_work.getMax_mark());
		assertNotNull(course_work.getCreation_date());
		assertNotNull(course_work.getDeadline());
		assertNotNull(course_work.getCourse());
		assertNotNull(course_work.toString());
	}
}
