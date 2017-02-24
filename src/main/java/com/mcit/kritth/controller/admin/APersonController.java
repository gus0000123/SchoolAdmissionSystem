package com.mcit.kritth.controller.admin;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;

@Controller
public class APersonController
{
	@Autowired
	private PersonBO service;
	@Autowired
	private DepartmentBO dservice;
	@Autowired
	private StudentBO sservice;
	@Autowired
	private StudentAdmissionStatusBO saservice;
	@Autowired
	private EmployeeBO eservice;
	
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ModelAndView personActionSelector(
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "actionPerformed", required = false) Boolean performed)
	{
		String url = ""; 
		if (mode == null) { mode = "view"; }
		if (performed == null) { performed = false; }
		
		switch(mode)
		{
			case "edit":
				if (!performed) url = "forward:/person/edit";
				else url = "forward:/person/edit/perform";
				break;
			case "insert":
				if (!performed) url = "forward:/person/insert";
				else url = "forward:/person/insert/perform";
				break;
			case "delete":
				url = "forward:/person/delete";
				break;
			case "view":
			default:
				url = "forward:/person/view";
				break;
		}
		
		System.out.println("Forward to " + url);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "person");
		model.addObject("mode", mode);
		
		return model;
	}
	
	@RequestMapping(value = "/person/view", method = RequestMethod.POST)
	public ModelAndView viewPerson()
	{
		String url = "layout/adminApp";
		List<Person> list = service.getAll();
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("list", list);
		
		return model;
	}
	
	private ModelAndView attachValues(int id, ModelAndView model)
	{
		List<Department> list = dservice.getAll();
		model.addObject("department_list", list);
		
		// Fetch data
		if (id >= 0)
		{
			// Try getting student
			try
			{
				Student s = sservice.getById(id);
				model.addObject("student", s);
			}
			catch (ObjectNotFoundException e) { }
				
			// Try getting employee
			try
			{
				Employee e = eservice.getById(id);
				model.addObject("employee", e);
			}
			catch (ObjectNotFoundException e) { }
		}
		
		return model;
	}
	
	// TODO: Add all parameters
	@RequestMapping(value = "/person/insert", method = RequestMethod.POST)
	public ModelAndView initInsertPerson()
	{
		String url = "layout/adminApp";
		
		ModelAndView model = new ModelAndView(url);
		
		model.addObject("person", new Person());
		
		return attachValues(-1, model); 
	}
	
	@RequestMapping(value = "/person/edit", method = RequestMethod.POST)
	public ModelAndView initEditPerson(
			@RequestParam("id") Integer id)
	{
		String url = "layout/adminApp";
		
		Person p;
		try	{ p = service.getById(id); }
		catch (ObjectNotFoundException ex) { return new ModelAndView("forward:/person/view"); }
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("person", p);
		return attachValues(id, model);
	}
	
	@RequestMapping(value = "/person/insert/perform", method = RequestMethod.POST)
	public ModelAndView doInsertPerson(
			@ModelAttribute("person") Person person)
	{
		String url = "forward:/person/view";
		service.insert(person);
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/person/edit/perform", method = RequestMethod.POST)
	public ModelAndView doEditPerson(
			@ModelAttribute("person") Person person)
	{
		String url = "forward:/person/edit/employee";
		
		Person p = service.getById(person.getID());
		p.copy(person);
		
		try { service.update(p); }
		catch (Exception e) { e.printStackTrace(); }
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value="/person/edit/employee", method = RequestMethod.POST)
	public ModelAndView doEditEmployee(
			@ModelAttribute("person") Person person,
			@RequestParam(value = "e_attach", required = false) List<String> attach,
			@RequestParam(value = "e_department") String department_id) throws Exception
	{
		String url = "forward:/person/edit/student";
		
		if (attach != null && attach.size() > 0 && attach.get(0).equals("attach"))
		{
			Employee employee = null;
			
			try
			{
				employee = eservice.getById(person.getID());
				employee.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				eservice.update(employee);
			}
			catch (ObjectNotFoundException ex)
			{
				employee = new Employee();
				employee.setPerson(service.getById(person.getID()));
				employee.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				eservice.insert(employee);
			}
		}
		else
		{
			eservice.delete(eservice.getById(person.getID()));
		}
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value="/person/edit/student", method = RequestMethod.POST)
	public ModelAndView doEditStudent(
			@ModelAttribute("person") Person person,
			@RequestParam(value = "s_attach", required = false) List<String> attachStudent,
			@RequestParam(value = "s_department") String department_id) throws Exception
	{
		String url = "forward:/person/view";
		
		if (attachStudent != null && attachStudent.size() > 0 && attachStudent.get(0).equals("attach"))
		{
			Student student = null;
			
			try
			{
				student = sservice.getById(person.getID());
				student.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				sservice.update(student);
			}
			catch (ObjectNotFoundException ex)
			{
				student = new Student();
				student.setAdmissionStatus(saservice.getById("Pending"));
				student.setPerson(service.getById(person.getID()));
				student.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				sservice.insert(student);
			}
		}
		else
		{
			sservice.delete(sservice.getById(person.getID()));
		}
		
		ModelAndView model = new ModelAndView(url);
		
		model.addObject("tab", "person");
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "/person/delete", method = RequestMethod.POST)
	public ModelAndView doDeletePerson(
			@RequestParam("selection") List<String> selection) throws Exception
	{
		String url = "forward:/person/view";
		
		for (String id : selection)
		{
			try
			{
				Person p = service.getById(Integer.parseInt(id));
				service.delete(p);
			} catch (Exception e) { e.printStackTrace(); }
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
}