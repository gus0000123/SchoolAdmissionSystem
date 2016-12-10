package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestStudentAdmissionStatus implements TestBean
{
	private StudentAdmissionStatus s;
	private String status;
	private String description;
	
	@Before
	@Override
	public void init()
	{
		s = new StudentAdmissionStatus();
		status = TestUtil.generateRandomString();
		description = TestUtil.generateRandomString();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		s.setStatus(status);
		s.setDescription(description);
		
		assertNotNull(s);
		assertNotNull(s.getStatus());
		assertNotNull(s.getDescription());
		assertNotNull(s.toString());
	}
}
