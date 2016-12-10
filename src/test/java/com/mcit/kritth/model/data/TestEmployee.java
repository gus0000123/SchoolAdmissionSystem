package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.TestUtil;

public class TestEmployee implements TestBean
{
	private Employee e;
	private int id;
	private double salary;
	private int rank;
	private Date start_date;
	private Person person;
	private Set<Course> assigned_courses;
	private Department department;
	
	@Before
	@Override
	public void init()
	{
		e = new Employee();
		id = TestUtil.generateRandomNumber();
		salary = TestUtil.generateRandomNumber();
		rank = TestUtil.generateRandomNumber();
		start_date = new Date();
		person = new Person();
		person.setAddress(new Address());
		assigned_courses = new HashSet<>();
		department = new Department();
	}
	
	@Test
	@Override
	public void testGetterSetter()
	{
		e.setId(id);
		e.setSalary(salary);
		e.setRank(rank);
		e.setStart_date(start_date);
		e.setPerson(person);
		e.setAssigned_courses(assigned_courses);
		e.setDepartment(department);
		
		assertNotNull(e);
		assertNotNull(e.getId());
		assertNotNull(e.getSalary());
		assertNotNull(e.getRank());
		assertNotNull(e.getStart_date());
		assertNotNull(e.getPerson());
		assertNotNull(e.getAssigned_courses());
		assertNotNull(e.getDepartment());
		assertNotNull(e.toString());
		
		e = new Employee();
		
		e.getAssigned_courses().add(new Course());
		
		assertNotNull(e.getAssigned_courses());
	}
}
