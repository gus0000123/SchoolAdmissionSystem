package com.mcit.kritth.controller.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;

@RunWith(MockitoJUnitRunner.class)
public class TestAPersonController
{
	@InjectMocks
	private APersonController controller;
	@Mock
	private PersonBO pservice;
	@Mock
	private DepartmentBO dservice;
	@Mock
	private StudentBO sservice;
	@Mock
	private StudentAdmissionStatusBO saservice;
	@Mock
	private EmployeeBO eservice;
	@Mock
	private List<Person> plist;
	@Mock
	private List<Department> dlist;
	@Mock
	private Person person;
	@Mock
	private Student student;
	@Mock
	private Employee employee;
	
	private MockMvc mockMvc;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testSelector() throws Exception
	{
		// Test null
		this.mockMvc.perform(post("/person"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/view"));
		
		// Test start edit
		this.mockMvc.perform(post("/person")
				.param("mode", "edit"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/edit"));
		
		// Test do edit
		this.mockMvc.perform(post("/person")
				.param("mode", "edit")
				.param("actionPerformed", "true"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/edit/perform"));
		
		// Test start insert
		this.mockMvc.perform(post("/person")
				.param("mode", "insert"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/insert"));
		
		// Test do insert
		this.mockMvc.perform(post("/person")
				.param("mode", "insert")
				.param("actionPerformed", "true"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/insert/perform"));
		
		// Test view
		this.mockMvc.perform(post("/person")
				.param("mode", "view"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/view"));
		
		// Test other
		this.mockMvc.perform(post("/person")
				.param("mode", "other"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person/view"));
	}
	
	@Test
	public void testViewServlet() throws Exception
	{
		Mockito.when(pservice.getAll()).thenReturn(plist);
		
		this.mockMvc.perform(post("/person/view"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("layout/adminApp"));
		
		Mockito.verify(pservice).getAll();
	}
	
	@Test
	public void testStartInsertServlet() throws Exception
	{
		Mockito.when(dservice.getAll()).thenReturn(dlist);
		
		this.mockMvc.perform(post("/person/insert"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("layout/adminApp"));
	}
	
	@Test
	public void testStartEditServlet() throws Exception
	{
		int id = 0;
		Mockito.when(pservice.getById(id)).thenReturn(person);
		Mockito.when(dservice.getAll()).thenReturn(dlist);
		Mockito.when(sservice.getById(id)).thenReturn(student);
		Mockito.when(eservice.getById(id)).thenReturn(employee);
		
		this.mockMvc.perform(post("/person/edit")
				.param("id", "" + id))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("layout/adminApp"));
	}
}
