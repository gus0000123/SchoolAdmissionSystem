package com.mcit.kritth.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestDepartment implements TestBean
{
	private Department department;
	private int dept_id;
	private int budget;
	private DepartmentCode dept_code;
	private Employee dean;
	private Date founding_date;
	
	@Before
	@Override
	public void init()
	{
		department = new Department();
		dept_id = TestUtil.generateRandomNumber();
		budget = TestUtil.generateRandomNumber();
		dept_code = new DepartmentCode();
		dept_code.setDept_code("test");
		dept_code.setDept_name("test");
		dean = new Employee();
		founding_date = new Date();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		department.setDeptId(dept_id);
		department.setBudget(budget);
		department.setCode(dept_code);
		department.setDean(dean);
		department.setFounding_date(founding_date);
		
		assertNotNull(department);
		assertNotNull(department.getDeptId());
		assertNotNull(department.getBudget());
		assertNotNull(department.getCode());
		assertNotNull(department.getDean());
		assertNotNull(department.getFounding_date());
		assertNotNull(department.toString());
	}
	
	@Test
	public void testOverride()
	{
		department.setCode(dept_code);		
		assertEquals(department, department);
		assertFalse(department.equals("String"));
		Department dpt2 = new Department();
		DepartmentCode dc2 = new DepartmentCode();
		dc2.setDept_code("admin");
		dc2.setDept_name("admin");
		dpt2.setCode(dc2);
		assertFalse(department.equals(dpt2));
	}
}
