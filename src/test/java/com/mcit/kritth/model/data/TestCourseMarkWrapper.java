package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestCourseMarkWrapper {
	private CourseMarkWrapper cmw;
	private List<CourseMark> cmlist;
	
	@Before
	public void init()
	{
		cmw = new CourseMarkWrapper();
		cmlist = new ArrayList<>();
	}
	
	@Test
	public void testGetterSetter()
	{
		assertNull(cmw.getList());
		cmw.setList(cmlist);
		assertNotNull(cmw.getList());
	}
}
