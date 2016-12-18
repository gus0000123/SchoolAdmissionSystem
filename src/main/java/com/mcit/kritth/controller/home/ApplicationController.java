package com.mcit.kritth.controller.home;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
@SessionAttributes("user")
public class ApplicationController
{
	@RequestMapping(value = "/homeApplication", method = RequestMethod.POST)
	public ModelAndView getApplicationContext(
			@ModelAttribute User u)
	{
		// Initialize
		ModelAndView model = new ModelAndView("layout/homeApp");
		Person p = u.getPerson();
		
		// Get Student
		StudentBO studentService = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		Student s = null;
				
		try
		{
			s = studentService.getById(p.getID());
			model.addObject("student", s);
		}
		catch (ObjectNotFoundException ex) { }
		
		// Get Employee
		EmployeeBO employeeService = ApplicationContextProvider.getApplicationContext().getBean("employeeService", EmployeeBO.class);
		Employee e = null;
		
		try
		{
			e = employeeService.getById(p.getID());
			model.addObject("employee", e);
		}
		catch (ObjectNotFoundException ex) { }
		
		model.addObject("tab", "application");
		
		return model;
	}
}
