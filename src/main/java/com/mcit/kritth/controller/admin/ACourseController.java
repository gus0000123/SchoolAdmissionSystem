package com.mcit.kritth.controller.admin;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;

@Controller
public class ACourseController
{
	@Autowired
	private CourseBO cservice;
	@Autowired
	private DepartmentBO dservice;
	@Autowired
	private EmployeeBO eservice;
	@Autowired
	private StudentBO sservice;
	
	@RequestMapping(value="/courseController", method=RequestMethod.POST)
	public ModelAndView courseContentSelector(
			@RequestParam(value="mode", required = false) String mode,
			@RequestParam(value="actionPerformed", required = false) Boolean performed)
	{
		String url = "forward:/courseView";
		
		if (mode == null) mode = "view";
		if (performed == null) performed = false;
		
		switch(mode)
		{
			case "insert":
				if (!performed) url = "forward:/courseStartInsert";
				else url = "forward:/courseDoInsert";
				break;
			case "edit":
				if (!performed) url = "forward:/courseStartEdit";
				else url = "forward:/courseDoEdit";
				break;
			case "delete":
				url = "forward:/courseDoDelete";
				break;
			case "view":
			default:
				url = "forward:/courseView";
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "course");
		model.addObject("mode", mode);
		
		return model;
	}
	
	@RequestMapping(value="/courseView", method=RequestMethod.POST)
	public ModelAndView courseView()
	{
		String url = "layout/adminApp";
		
		List<Course> list = cservice.getAll();
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("list", list);
		model.addObject("mode", "view");
		
		return model;
	}
	
	private ModelAndView attachValues(String course_code, ModelAndView model)
	{
		// Get course if available
		if (course_code != null)
		{
			try
			{
				Course course = cservice.getById(course_code);
				model.addObject("course", course);
			}
			catch (ObjectNotFoundException e) { }
		}
		
		// Get all departments
		try
		{
			List<Department> dlist = dservice.getAll();
			model.addObject("department_list", dlist);
		}
		catch (ObjectNotFoundException e) { }
		
		// Get all instructor
		try
		{
			List<Employee> all_employees = eservice.getAll();
			model.addObject("instructor_list", all_employees);
		}
		catch (ObjectNotFoundException e) { }
		
		return model;
	}
	
	@RequestMapping(value="/courseStartInsert", method=RequestMethod.POST)
	public ModelAndView courseStartInsert()
	{
		String url = "layout/adminApp";
		
		ModelAndView model = new ModelAndView(url);
		
		return attachValues(null, model);
	}
	
	@RequestMapping(value="/courseStartEdit", method=RequestMethod.POST)
	public ModelAndView courseStartEdit(
			@RequestParam(value="course_code") String course_code)
	{
		String url = "layout/adminApp";
		
		ModelAndView model = new ModelAndView(url);
		
		return attachValues(course_code, model);
	}
	
	@RequestMapping(value="/courseDoInsert", method=RequestMethod.POST)
	public ModelAndView courseDoInsert(
			@RequestParam(value="c_class_level") Integer class_level,
			@RequestParam(value="c_course_number") Integer course_number,
			@RequestParam(value="c_section") Integer section,
			@RequestParam(value="c_course_name") String course_name,
			@RequestParam(value="c_department") String department_id,
			@RequestParam(value="c_instructor") String employee_id)
	{
		String url = "forward:/courseView";
		
		Course course = new Course();
		course.setClass_level(class_level);
		course.setCourse_number(course_number);
		course.setSection(section);
		course.setCourse_name(course_name);
		
		course.setDepartment(dservice.getById(Integer.parseInt(department_id)));
		course.setInstructor(eservice.getById(Integer.parseInt(employee_id)));
		
		course.getCourse_code();			// Auto generate course_code
		
		try { cservice.insert(course); } // In case course_code is the same, do nothing for now
		catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value="/courseDoEdit", method=RequestMethod.POST)
	public ModelAndView courseDoEdit(
			@RequestParam(value="c_course_code") String course_code,
			@RequestParam(value="c_class_level") Integer class_level,
			@RequestParam(value="c_course_number") Integer course_number,
			@RequestParam(value="c_section") Integer section,
			@RequestParam(value="c_course_name") String course_name,
			@RequestParam(value="c_department") String department_id,
			@RequestParam(value="c_instructor") String employee_id,
			@RequestParam(value="student_selection", required = false) List<String> student_id,
			@RequestParam(value="coursework_selection", required = false) List<String> coursework_id)
	{
		String url = "forward:/courseView";
		
		Course course = new Course();
		course.setClass_level(class_level);
		course.setCourse_number(course_number);
		course.setSection(section);
		course.setCourse_name(course_name);
		
		course.setDepartment(dservice.getById(Integer.parseInt(department_id)));
		course.setInstructor(eservice.getById(Integer.parseInt(employee_id)));
		
		course.setCourse_code(course_code);
		
		course.getStudents().clear();
		
		if (student_id != null)
		{
			for (String sid : student_id)
			{
				Student s = sservice.getById(Integer.parseInt(sid));
				course.getStudents().add(s);
			}
		}
		
		try { cservice.update(course); }
		catch (Exception e) { e.printStackTrace(); }
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "course");
		
		return model;
	}
	
	@RequestMapping(value="/courseDoDelete", method=RequestMethod.POST)
	public ModelAndView courseDoDelete(
			@RequestParam(value="selection") List<String> selection)
	{
		String url = "forward:/courseView";
		
		for (String id : selection)
		{
			Course course = cservice.getById(id);
			for (Student s : course.getStudents())
			{
				s.getEnrolled_courses().remove(course);
				sservice.update(s);
			}
			try { cservice.delete(course); }
			catch(Exception ex) { }
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
}