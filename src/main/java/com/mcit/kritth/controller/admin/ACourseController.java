package com.mcit.kritth.controller.admin;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.util.BeanUtil;

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
	@Autowired
	private CourseWorkBO cwservice;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Department.class, "department", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text)
			{
				List<Department> list = dservice.getAll();
				for (Department d : list)
				{
					if (d.getDept_code().getDept_name().equals(text))
					{
						setValue(d);
						return;
					}
				}
			}
		});
		binder.registerCustomEditor(Employee.class, "instructor", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text)
			{
				int id = Integer.parseInt(text);
				Employee instructor = eservice.getById(id);
				setValue(instructor);
			}
		});
		binder.registerCustomEditor(Set.class, "students", new PropertyEditorSupport() {
			@SuppressWarnings("unchecked")
			@Override
			public String getAsText()
			{
				Set<Student> students = (Set<Student>) this.getValue();
				String result = "";
				for (Student s : students)
				{
					result += s.getId() + ",";
				}
				return result;
			}
			
			@Override
			public void setAsText(String text)
			{
				String[] list = text.split(",");
				Set<Student> students = new HashSet<>();
				for (int i = 0; i < list.length; i++)
				{
					students.add(sservice.getById(Integer.parseInt(list[i])));
				}
				setValue(students);
			}
		});
		binder.registerCustomEditor(Set.class, "course_works", new PropertyEditorSupport() {
			@SuppressWarnings("unchecked")
			@Override
			public String getAsText()
			{
				Set<CourseWork> courseworks = (Set<CourseWork>) this.getValue();
				String result = "";
				for (CourseWork cw : courseworks)
				{
					result += cw.getCoursework_id() + ",";
				}
				return result;
			}
			
			@Override
			public void setAsText(String text)
			{
				String[] list = text.split(",");
				Set<CourseWork> courseworks = new HashSet<>();
				for (int i = 0; i < list.length; i++)
				{
					courseworks.add(cwservice.getById(Integer.parseInt(list[i])));
				}
				setValue(courseworks);
			}
		});
	}
	
	@RequestMapping(value="/course", method=RequestMethod.POST)
	public ModelAndView courseContentSelector(
			@RequestParam(value="mode", required = false) String mode,
			@RequestParam(value="actionPerformed", required = false) Boolean performed)
	{
		String url = "forward:/course/view";
		
		if (mode == null) mode = "view";
		if (performed == null) performed = false;
		
		switch(mode)
		{
			case "insert":
				if (!performed) url = "forward:/course/insert";
				else url = "forward:/course/insert/perform";
				break;
			case "edit":
				if (!performed) url = "forward:/course/edit";
				else url = "forward:/course/edit/perform";
				break;
			case "delete":
				url = "forward:/course/delete";
				break;
			case "view":
			default:
				url = "forward:/course/view";
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "course");
		model.addObject("mode", mode);
		
		System.out.println("Go to " + url);
		
		return model;
	}
	
	@RequestMapping(value="/course/view", method=RequestMethod.POST)
	public ModelAndView courseView()
	{
		String url = "layout/adminApp";
		
		List<Course> list = cservice.getAll();
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("list", list);
		model.addObject("tab", "course");
		model.addObject("mode", "view");
		
		return model;
	}
	
	private ModelAndView attachValues(String course_code, ModelAndView model)
	{
		// Get course if available
		Course course = null;
		if (course_code != null)
		{
			try
			{
				course = cservice.getById(course_code);
				model.addObject("course", course);
			}
			catch (ObjectNotFoundException e)
			{ 
				model.addObject("course", new Course());
				model.addObject("mode", "insert");
			}
		}
		else
		{
			model.addObject("course", new Course());
		}
		
		try
		{
			List<Department> dlist = dservice.getAll();
			model.addObject("department_list", dlist);
			
			List<Employee> all_employees = eservice.getAll();
			model.addObject("instructor_list", all_employees);
			
			if (course != null)
			{
				Student[] students = course.getStudents().toArray(new Student[course.getStudents().size()]);
				model.addObject("all_students", students);
				
				System.out.println("Course has " + course.getCourse_works().size());
				CourseWork[] courseworks = course.getCourse_works().toArray(new CourseWork[course.getCourse_works().size()]);
				model.addObject("all_courseworks", courseworks);
			}
		}
		catch (ObjectNotFoundException e)
		{
			model = new ModelAndView("forward:/course/view");
		}
		
		return model;
	}
	
	@RequestMapping(value="/course/insert", method=RequestMethod.POST)
	public ModelAndView courseStartInsert()
	{
		String url = "layout/adminApp";
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "insert");
		return attachValues(null, model);
	}
	
	@RequestMapping(value="/course/edit", method=RequestMethod.POST)
	public ModelAndView courseStartEdit(
			@RequestParam(value="course_code") String course_code)
	{
		String url = "layout/adminApp";
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "edit");
		return attachValues(course_code, model);
	}
	
	@RequestMapping(value="/course/insert/perform", method=RequestMethod.POST)
	public ModelAndView courseDoInsert(
			@ModelAttribute("course") Course course)
	{
		String url = "forward:/course/view";
		
		course.setCourse_code(BeanUtil.getCourseCode(course));
		
		try { cservice.insert(course); } // In case course_code is the same, do nothing for now
		catch (Exception e) { e.printStackTrace(); }
		
		System.out.println("Here");
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value="/course/edit/perform", method=RequestMethod.POST)
	public ModelAndView courseDoEdit(
			@ModelAttribute("course") Course course,
			BindingResult result)
	{
		String url = "forward:/course/view";
		
		try { cservice.update(course); }
		catch (Exception e) { e.printStackTrace(); }
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value="/course/delete", method=RequestMethod.POST)
	public ModelAndView courseDoDelete(
			@RequestParam(value="selection") List<String> selection)
	{
		String url = "forward:/course/view";
		
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
		return model;
	}
}