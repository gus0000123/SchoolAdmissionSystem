package com.mcit.kritth.controller.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpSessionRequiredException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mcit.kritth.model.data.User;

@RunWith(MockitoJUnitRunner.class)
public class TestAdminController
{
	@InjectMocks
	private AdminController controller;
	
	@Mock
	private User user;
	
	private MockMvc mockMvc;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testNullTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person"));
	}
	
	@Test
	public void testStudentTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user)
				.param("tab", "student"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/student"));
	}
	
	@Test
	public void testCourseTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user)
				.param("tab", "course"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/course"));
	}
	
	@Test
	public void testCourseWorkTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user)
				.param("tab", "coursework"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/coursework"));
	}
	
	@Test
	public void testCourseMarkTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user)
				.param("tab", "coursemark"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/coursemark"));
	}
	
	@Test
	public void testPersonTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user)
				.param("tab", "person"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person"));
	}
	
	@Test
	public void testInvalidTab() throws Exception
	{
		this.mockMvc.perform(post("/admin")
				.sessionAttr("user", user)
				.param("tab", "other"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/person"));
	}
	
	@Test(expected = HttpSessionRequiredException.class)
	public void testNoSession() throws Exception
	{
		this.mockMvc.perform(post("/admin"))
				.andExpect(status().is5xxServerError());
	}
}
