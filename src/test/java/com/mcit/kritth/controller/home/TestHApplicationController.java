package com.mcit.kritth.controller.home;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hibernate.ObjectNotFoundException;

import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;

@RunWith(MockitoJUnitRunner.class)
public class TestHApplicationController
{
	@InjectMocks
	private HApplicationController controller;
	
	@Mock
	private StudentBO studentService;
	
	@Mock
	private EmployeeBO employeeService;
	
	@Mock
	private ModelMap model;
	
	@Mock
	private Person person;
	
	@Mock
	private Student student;
	
	@Mock
	private Employee employee;
	
	@Mock
	private User user;
	
	private MockMvc mockMvc;
	
	@Before
	public void Init()
	{
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		when(user.getPerson()).thenReturn(person);
	}
	
	@Test
	public void testNormalRun() throws Exception
	{
		int id = 0;
		when(studentService.getById(id)).thenReturn(student);
		when(employeeService.getById(id)).thenReturn(employee);
		
		this.mockMvc.perform(post("/homeApplication")
				.sessionAttr("user", user))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("layout/homeApp"));
	}
	
	@Test
	public void testDatabaseException() throws Exception
	{
		int id = 0;
		when(studentService.getById(id)).thenThrow(new ObjectNotFoundException(id, null));
		when(employeeService.getById(id)).thenThrow(new ObjectNotFoundException(id, null));
		
		this.mockMvc.perform(post("/homeApplication")
				.sessionAttr("user", user))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("layout/homeApp"));
	}
}