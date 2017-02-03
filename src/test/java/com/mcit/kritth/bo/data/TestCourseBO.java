package com.mcit.kritth.bo.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.bo.template.EmployeeBO;
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
	private Set<Student> ss;
	@Mock
	private Set<Course> sc;
	@Mock
	private CourseDAO dao;
	@Mock
	private EmployeeBO eservice;
	@InjectMocks
	private CourseBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		instance.setDepartment(d);
		when(service.getById(instance.getCourse_code())).thenReturn(instance);
		when(instance.getInstructor()).thenReturn(e);
		instance.setStudents(ss);
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
		service.update(instance);
		verify(dao).updateBean(instance);
	}
	
	@Test
	public void testStudentBidirectionalUpdate() {
		String id = "test";
		Course secondary = mock(Course.class);
		Employee e2 = mock(Employee.class);
		when(service.getById(id)).thenReturn(secondary);
		when(secondary.getStudents()).thenReturn(ss);
		when(instance.getInstructor()).thenReturn(e);
		when(secondary.getInstructor()).thenReturn(e2);
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
	
	@Test
	public void testDeleteToRemoveInstructor() {
		// Test TT
		String id = "test";
		when(instance.getCourse_code()).thenReturn(id);
		when(instance.getInstructor()).thenReturn(e);
		when(instance.getInstructor().getAssigned_courses()).thenReturn(sc);
		when(instance.getInstructor().getAssigned_courses().contains(instance)).thenReturn(true);
		/*Mockito.doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				return null;
			}
		}).when(sc).remove(instance);*/
		service.delete(instance);
		verify(sc).remove(instance);
		verify(eservice).update(instance.getInstructor());
		verify(dao).removeBeanByPrimaryKey(instance.getCourse_code());
	}
	
	@Test
	public void testDeleteWhenInstructorNoCourse() {
		// Test TF
		String id = "test";
		when(instance.getCourse_code()).thenReturn(id);
		when(instance.getInstructor()).thenReturn(e);
		service.delete(instance);
		verify(dao).removeBeanByPrimaryKey(instance.getCourse_code());
	}
	
	@Test
	public void testDeleteAllFalse() {
		String id = "test";
		when(instance.getCourse_code()).thenReturn(id);
		when(instance.getInstructor()).thenReturn(null);
		service.delete(instance);
		verify(dao).removeBeanByPrimaryKey(instance.getCourse_code());
	}
}