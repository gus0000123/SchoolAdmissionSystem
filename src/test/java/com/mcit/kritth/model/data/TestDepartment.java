package com.mcit.kritth.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		department.setDeptId(dept_id);
		department.setBudget(budget);
		department.setDept_code(dept_code);
		department.setDean(dean);
		
		assertNotNull(department);
		assertNotNull(department.getDeptId());
		assertNotNull(department.getBudget());
		assertNotNull(department.getDept_code());
		assertNotNull(department.getDean());
		assertNotNull(department.toString());
	}
	
	@Test
	public void testOverride()
	{
		department.setDept_code(dept_code);		
		assertEquals(department, department);
		assertFalse(department.equals("String"));
		Department dpt2 = new Department();
		DepartmentCode dc2 = new DepartmentCode();
		dc2.setDept_code("admin");
		dc2.setDept_name("admin");
		dpt2.setDept_code(dc2);
		assertFalse(department.equals(dpt2));
	}
}
