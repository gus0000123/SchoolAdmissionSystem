package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestStudentGrade implements TestBean
{
	private StudentGrade s;
	private int gradeId;
	private Date start_date;
	private Student student;
	private Course course;
	private Set<CourseMark> courseMarks;
	
	@Before
	@Override
	public void init()
	{
		s = new StudentGrade();
		gradeId = TestUtil.generateRandomNumber();
		start_date = new Date();
		student = new Student();
		course = new Course();
		courseMarks = new HashSet<>();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		s.setGradeId(gradeId);
		s.setStart_date(start_date);
		s.setStudent(student);
		s.setCourse(course);
		s.setCourseMarks(courseMarks);
		
		assertNotNull(s);
		assertNotNull(s.getGradeId());
		assertNotNull(s.getStart_date());
		assertNotNull(s.getStudent());
		assertNotNull(s.getCourse());
		assertNotNull(s.getCourseMarks());
		assertNotNull(s.toString());
		
		s = new StudentGrade();
		
		s.getCourseMarks().add(new CourseMark());
		
		assertNotNull(s.getCourseMarks());
	}
	
	
}
