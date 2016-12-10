package com.mcit.kritth.bo.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

import org.hibernate.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.DepartmentCodeBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.DepartmentCode;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.spring.ApplicationContextProvider;
import com.mcit.kritth.util.BeanUtil;
import com.mcit.kritth.util.TestUtil;

import spring.StartSpring;

/**
 * Testing department, course, and departmet code
 * @author Best
 *
 */
public class TestDataService implements TestService
{
	private boolean requireDelete;
	
	private Course course;
	private Department department;
	private DepartmentCode department_code;
	private Employee employee;
	private Person person;
	private CourseBO cService;
	private DepartmentBO dService;
	private DepartmentCodeBO dcService;
	private EmployeeBO eService;
	private PersonBO pService;

	@Before
	@Override
	public void init()
	{
		requireDelete = true;
		
		StartSpring.init();
		dcService = ApplicationContextProvider.getApplicationContext().getBean("departmentCodeService", DepartmentCodeBO.class);
		department_code = new DepartmentCode();
		department_code.setDept_code(TestUtil.generateRandomString());
		department_code.setDept_name(TestUtil.generateRandomString());
		
		dService = ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class);
		department = new Department();
		department.setBudget(TestUtil.generateRandomNumber());
		department.setCode(department_code);
		
		cService = ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class);
		course = new Course();
		course.setClass_level(TestUtil.generateRandomNumber(9));
		course.setCourse_number(TestUtil.generateRandomNumber(9));
		course.setSection(TestUtil.generateRandomNumber(9));
		course.setCourse_name(TestUtil.generateRandomString());
		course.setDepartment(department);
		course.setCourse_code(BeanUtil.getCourseCode(course));
		
		pService = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
		person = new Person();
		person.setFirstName(TestUtil.generateRandomString());
		person.setLastName(TestUtil.generateRandomString());
		person.setEmail(TestUtil.generateRandomString());
		
		eService = ApplicationContextProvider.getApplicationContext().getBean("employeeService", EmployeeBO.class);
		employee = new Employee();
		employee.setSalary(TestUtil.generateRandomNumber());
		
		course.setInstructor(employee);
		
		// Insert data to test
		pService.insert(person);
		employee.setPerson(person);
		eService.insert(employee);
		dcService.insert(department_code);
		dService.insert(department);
		cService.insert(course);
	}

	@Test
	@Override
	public void testInsert()
	{
		assertNotNull(pService.getById(person.getID()));
		assertNotNull(eService.getById(employee.getId()));
		assertNotNull(dcService.getById(department_code.getDept_code()));
		assertNotNull(dService.getById(department.getDeptId()));
		assertNotNull(cService.getById(course.getCourse_code()));
	}

	@Test
	@Override
	public void testUpdate()
	{
		// Change name
		String sbuffer = TestUtil.generateRandomString();
		String dname = new String(department_code.getDept_name());
		while (sbuffer.equals(dname)) { sbuffer = TestUtil.generateRandomString(); }
		department_code.setDept_name(sbuffer);
		
		// Change budget
		int ibuffer = TestUtil.generateRandomNumber();
		int dbudget = department.getBudget();
		while (ibuffer == dbudget) { ibuffer = TestUtil.generateRandomNumber(); }
		department.setBudget(ibuffer);
		
		// Change name
		sbuffer = TestUtil.generateRandomString();
		String cname = new String(course.getCourse_name());
		while (sbuffer.equals(cname)) { sbuffer = TestUtil.generateRandomString(); }
		course.setCourse_name(sbuffer);
		
		// change name
		sbuffer = TestUtil.generateRandomString();
		String pname = new String(person.getFirstName());
		while (sbuffer.equals(pname)) { sbuffer = TestUtil.generateRandomString(); }
		person.setFirstName(sbuffer);
		
		// change salary
		ibuffer = TestUtil.generateRandomNumber();
		double esalary = employee.getSalary();
		while (ibuffer == esalary) { ibuffer = TestUtil.generateRandomNumber(); }
		employee.setSalary(ibuffer);
		
		// Testing update
		dcService.update(department_code);
		dService.update(department);
		cService.update(course);
		eService.update(employee);
		pService.update(person);
		
		// Load back
		department_code = dcService.getById(department_code.getDept_code());
		department = dService.getById(department.getDeptId());
		course = cService.getById(course.getCourse_code());
		employee = eService.getById(employee.getId());
		person = pService.getById(person.getID());
		
		assertNotEquals(department_code.getDept_name(), dname);
		assertNotEquals(department.getBudget(), dbudget);
		assertNotEquals(course.getCourse_name(), cname);
		assertNotEquals(employee.getSalary(), esalary);
		assertNotEquals(person.getFirstName(), pname);
	}

	@Test
	@Override
	public void testGetAll()
	{
		List<DepartmentCode> dcList = dcService.getAll();
		List<Department> dList = dService.getAll();
		List<Course> cList = cService.getAll();
		List<Employee> eList = eService.getAll();
		List<Person> pList = pService.getAll();
		
		assertTrue(dcList.size() > 0);
		assertTrue(dList.size() > 0);
		assertTrue(cList.size() > 0);
		assertTrue(eList.size() > 0);
		assertTrue(pList.size() > 0);
	}
	
	@Test
	@Override
	public void testDelete()
	{
		cService.deleteById(course.getCourse_code());
		dService.deleteById(department.getDeptId());
		dcService.deleteById(department_code.getDept_code());
		pService.deleteById(person.getID());
		
		try { department_code = dcService.getById(department_code.getDept_code()); }
		catch (ObjectNotFoundException ex) { department_code = null; }
		
		try { department = dService.getById(department.getDeptId()); }
		catch (ObjectNotFoundException ex) { department = null; }
		
		try { course = cService.getById(course.getCourse_code()); }
		catch (ObjectNotFoundException ex) { course = null; }
		
		try { employee = eService.getById(employee.getId()); }
		catch (ObjectNotFoundException ex) { employee = null; }
		
		try { person = pService.getById(person.getID()); }
		catch (ObjectNotFoundException ex) { person = null; }
		
		assertNull(department_code);
		assertNull(department);
		assertNull(course);
		assertNull(employee);
		assertNull(person);
		
		requireDelete = false;
	}

	@After
	@Override
	public void clean()
	{
		if (requireDelete)
		{
			cService.delete(course);
			dService.delete(department);
			dcService.delete(department_code);
			pService.delete(person);
		}
	}
}
