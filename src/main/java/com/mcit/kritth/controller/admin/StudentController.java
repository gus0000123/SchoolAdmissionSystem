package com.mcit.kritth.controller.admin;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
public class StudentController
{
	@RequestMapping(value = "studentController")
	public ModelAndView studentActionSelector(
			@RequestParam(value = "studentAction", required = false) String action)
	{
		String url = "forward:/personView";
		if (action != null)
		{
			switch(action)
			{ 
				case "insert":
					url = "forward:/studentDoInsert";
					break;
				case "delete":
					url = "forward:/studentDoDelete";
					break;
			}
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "studentDoInsert")
	public ModelAndView studentInitInsert(
			@RequestParam("attachStudent") List<String> attachStudent,
			@RequestParam("id") String id,
			@RequestParam(value = "s_department") String department_id)
	{
		String url = "forward:/personView";
		
		// Attach student
		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		if (attachStudent.size() > 0 && attachStudent.get(0).equals("attach"))
		{
			DepartmentBO dservice = ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class);
			
			Student student = null;
			
			try
			{
				student = service.getById(Integer.parseInt(id));
				student.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				service.update(student);
			}
			catch (ObjectNotFoundException ex)
			{
				StudentAdmissionStatusBO saservice = ApplicationContextProvider.getApplicationContext().getBean("studentAdmissionStatusService", StudentAdmissionStatusBO.class); 
				PersonBO pservice = ApplicationContextProvider.getApplicationContext().getBean("personService", PersonBO.class);
				
				student = new Student();
				student.setAdmissionStatus(saservice.getById("Pending"));
				student.setPerson(pservice.getById(Integer.parseInt(id)));
				student.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				service.insert(student);
			}
		}
		else
		{
			service.deleteById(Integer.parseInt(id));
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "studentDoDelete")
	public ModelAndView studentInitInsert(
			@RequestParam("id") String id)
	{
		String url = "forward:/personView";

		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		try { service.deleteById(Integer.parseInt(id)); }
		catch (ObjectNotFoundException e) { }
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
}
