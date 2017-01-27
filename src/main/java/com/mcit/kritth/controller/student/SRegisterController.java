package com.mcit.kritth.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.User;

@Controller
@SessionAttributes("user")
public class SRegisterController
{
	@Autowired
	private StudentBO sservice;
	@Autowired
	private DepartmentBO dservice;
	@Autowired
	private CourseBO cservice;
	
	@RequestMapping(value = "/studentCourseRegister", method = RequestMethod.POST)
	public ModelAndView courseRegisterSelector(
			@ModelAttribute User user,
			@RequestParam(value = "mode", required = false) String mode)
	{
		if (mode == null) mode = "list";
		
		String url = "layout/studentApp";
		
		switch (mode)
		{
			case "list":
			default:
				url = "forward:/studentRegisterView";
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		
		model.addObject("tab", "register");
		
		return model;
	}
	
	@RequestMapping(value = "/studentRegisterView", method = RequestMethod.POST)
	public ModelAndView courseRegistrationView(
			@RequestParam(value="department_id", required=false) String department_id)
	{
		// Get departments
		List<Department> allDepartments = getDepartments();
		Department department = null; 
		if (department_id != null)
		{
			try { department = dservice.getById(Integer.parseInt(department_id)); }
			catch (ObjectNotFoundException ex) { }
		}
		else
		{
			if (allDepartments != null && allDepartments.size() > 0)
			{
				department = allDepartments.get(0);
			}
		}
		
		ModelAndView model = new ModelAndView("layout/studentApp");
		
		model.addObject("mode", "list");
		model.addObject("department_list", allDepartments);
		model.addObject("department", department);
		model.addObject("course_list", getCourseByDepartment(department));
		
		return model;
	}
	
	private List<Department> getDepartments()
	{
		List<Department> result = null;
		
		try { result = dservice.getAll(); }
		catch (ObjectNotFoundException ex) { return null; }
		
		if (result.size() > 1)
			result.sort((o1, o2) -> o1.getCode().getDept_code().compareTo(o2.getCode().getDept_code()));
		
		return result;
	}
	
	private List<Course> getCourseByDepartment(Department d)
	{
		List<Course> result = new ArrayList<>();
		
		try
		{
			List<Course> allCourses = cservice.getAll();
			for (Course c : allCourses)
			{
				if (c.getDepartment().equals(d) && c.isIs_active())
					result.add(c);
			}
			
			result.sort((o1, o2) -> o1.getDepartment().getCode().getDept_name().compareTo(o2.getDepartment().getCode().getDept_name()));
			
			return result;
		}
		catch (ObjectNotFoundException ex) { return null; }
	}
}
