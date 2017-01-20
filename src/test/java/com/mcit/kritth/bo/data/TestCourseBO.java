package com.mcit.kritth.bo.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.Set;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.dao.template.CourseDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseBO implements TestService
{
	@Mock
	private Course instance;
	@Mock
	private Employee e;
	@Mock
	private Department d;
	@Mock
	private Set<Student> s;
	@Mock
	private CourseDAO dao;
	@InjectMocks
	private CourseBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		instance.setDepartment(d);
		instance.setInstructor(e);
		instance.setStudents(s);
	}

	@Test
	@Override
	public void testInsert()
	{
		service.insert(instance);
		verify(dao).insertBean(instance);
	}

	@Test
	@Override
	public void testUpdate() {
		when(service.getById(instance.getCourse_code())).thenReturn(instance);
		when(instance.getInstructor()).thenReturn(e);
		service.update(instance);
		verify(dao).updateBean(instance);
	}

	@Test
	@Override
	public void testGet() {
		String id = "test";
		service.getById(id);
		verify(dao).getModelByPrimaryKey(id);
	}

	@Test
	@Override
	public void testGetAll() {
		service.getAll();
		verify(dao).getAllBeans();
	}

	@Test
	@Override
	public void testDelete() {
		String id = "test";
		when(instance.getCourse_code()).thenReturn(id);
		service.delete(instance);
		verify(dao).removeBeanByPrimaryKey(instance.getCourse_code());
	}
}
