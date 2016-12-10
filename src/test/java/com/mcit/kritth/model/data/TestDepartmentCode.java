package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestDepartmentCode implements TestBean
{
	private DepartmentCode dc;
	private String dept_code;
	private String dept_name;
	
	@Before
	@Override
	public void init()
	{
		dc = new DepartmentCode();
		dept_code = TestUtil.generateRandomString();
		dept_name = TestUtil.generateRandomString();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		dc.setDept_code(dept_code);
		dc.setDept_name(dept_name);
		
		assertNotNull(dc);
		assertNotNull(dc.getDept_code());
		assertNotNull(dc.getDept_name());
		assertNotNull(dc.toString());
	}

}
