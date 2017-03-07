package com.mcit.kritth.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.DepartmentCodeBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.DepartmentCode;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;

@Controller
public class ADepartmentController
{
	@Autowired
	private DepartmentBO dservice;
	
	@Autowired
	private DepartmentCodeBO dcservice;
	
	@Autowired
	private StudentBO sservice;
	
	@Autowired
	private CourseBO cservice;
	
	@Autowired
	private EmployeeBO eservice;
	
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public ModelAndView departmentContentSelector(
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "actionPerformed", required = false) boolean performed)
	{
		String url = "forward:/department/view";
		
		if (mode == null) mode = "view";
		switch(mode)
		{ 
			case "insert":
				if (!performed) url = "forward:/department/insert";
				else url = "forward:/department/insert/perform";
				break;
			case "edit":
				if (!performed) url = "forward:/department/edit";
				else url = "forward:/department/edit/perform";
				break;
			case "delete":
				url = "forward:/department/delete";
				break;
			case "view":
			default:
				url = "forward:/department/view";
				break;
		}
		
		System.out.println("Go to " + url);
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/department/view", method = RequestMethod.POST)
	public ModelAndView listDepartments()
	{
		String url = "layout/adminApp";
		
		List<Department> list = dservice.getAll();
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("list", list);
		model.addObject("tab", "department");
		model.addObject("mode", "view");
		return model;
	}
	
	@RequestMapping(value = "/department/insert", method = RequestMethod.POST)
	public ModelAndView departmentStartInsert()
	{
		String url = "layout/adminApp";
		
		Department department = new Department();
		DepartmentCode dc = new DepartmentCode();
		department.setDept_code(dc);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "insert");
		model.addObject("tab", "department");
		model.addObject("department", department);
		return model;
	}
	
	@RequestMapping(value = "/department/insert/perform", method = RequestMethod.POST)
	public ModelAndView departmentDoInsert(
			@ModelAttribute("department") Department department)
	{
		String url = "forward:/department/view";
		
		try
		{
			dcservice.insert(department.getDept_code());
			dservice.insert(department);
		}
		catch (Exception e) { e.printStackTrace(); }
		
		ModelAndView model = new ModelAndView(url);
		return model;
	}
	
	@RequestMapping(value = "/department/edit", method = RequestMethod.POST)
	public ModelAndView departmentStartEdit(
			@RequestParam("id") int department_id)
	{
		String url = "layout/adminApp";
		
		Department department = dservice.getById(department_id);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "edit");
		model.addObject("tab", "department");
		model.addObject("department", department);
		return model;
	}
	
	@RequestMapping(value = "/department/edit/perform", method = RequestMethod.POST)
	public ModelAndView departmentDoEdit(
			@ModelAttribute("department") Department department)
	{
		String url = "forward:/department/view";
		
		dcservice.update(department.getDept_code());
		dservice.update(department);
		
		ModelAndView model = new ModelAndView(url);
		return model;
	}
	
	@RequestMapping(value = "/department/delete", method = RequestMethod.POST)
	public ModelAndView departmentDoDelete(
			@RequestParam(value="selection") List<String> selection)
	{
		String url = "forward:/department/view";
		
		for (String id : selection)
		{
			Department department = dservice.getById(Integer.parseInt(id));
			DepartmentCode dc = department.getDept_code();
			
			// Delete Employee
			List<Employee> empList = eservice.getAll();
			for (Employee e : empList)
			{
				if (e.getDepartment().equals(department))
				{
					try { eservice.delete(e); }
					catch (Exception ex) { ex.printStackTrace(); }
				}
			}
			
			// Delete Student
			List<Student> studentList = sservice.getAll();
			for (Student s : studentList)
			{
				if (s.getDepartment().equals(department))
				{
					try { sservice.delete(s); }
					catch (Exception ex) { ex.printStackTrace(); }
				}
			}
			
			
			// Delete Course
			List<Course> courseList = cservice.getAll();
			for (Course c : courseList)
			{
				if (c.getDepartment().equals(department))
				{
					try { cservice.delete(c); }
					catch (Exception ex) { ex.printStackTrace(); }
				}
			}
			
			try { dservice.delete(department); }
			catch (Exception ex) { ex.printStackTrace(); }
			
			try { dcservice.delete(dc); }
			catch (Exception ex) { ex.printStackTrace(); }
		}
		
		ModelAndView model = new ModelAndView(url);
		return model;
	}
}
