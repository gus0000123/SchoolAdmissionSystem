package com.mcit.kritth.controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
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
import com.mcit.kritth.spring.ApplicationContextProvider;
import com.mcit.kritth.util.BidirectionalUtil;

@Controller
public class ACourseController
{
	private CourseBO cservice;
	private DepartmentBO dservice;
	private EmployeeBO eservice;
	private StudentBO sservice;
	private CourseWorkBO cwservice;
	
	private void init()
	{
		cservice = ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class);
		dservice = ApplicationContextProvider.getApplicationContext().getBean(DepartmentBO.class);
		eservice = ApplicationContextProvider.getApplicationContext().getBean(EmployeeBO.class);
		sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
		cwservice = ApplicationContextProvider.getApplicationContext().getBean(CourseWorkBO.class);
	}
	
	@RequestMapping(value="/courseController", method=RequestMethod.POST)
	public ModelAndView courseContentSelector(
			@RequestParam(value="mode", required = false) String mode,
			@RequestParam(value="actionPerformed", required = false) Boolean performed)
	{
		if (cservice == null) init();
		
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
	
	private void studentCourseUpdate(List<String> student_id, Course course)
	{
		if (student_id != null)
		{
			// Add course to new students
			for (String sid : student_id)
			{
				Student s = sservice.getById(Integer.parseInt(sid));
				BidirectionalUtil.addIfNew(s, sservice, course, s.getEnrolled_courses());
			}
			
			// Remove course from old students
			for (Student s : course.getStudents())
				BidirectionalUtil.removeIfOld(s, sservice, course, s.getEnrolled_courses(), "" + s.getId(), student_id);
		}
		else
		{
			// Remove course from all students
			for (Student s : course.getStudents())
				BidirectionalUtil.remove(s, sservice, course, s.getEnrolled_courses());
		}
		
		course.getStudents().clear();
		
		// Add student to Courses
		if (student_id != null)
		{
			for (String sid : student_id)
				BidirectionalUtil.add(Integer.parseInt(sid), sservice, course.getStudents());
		}
	}
	
	// Can only delete
	private Set<CourseWork> courseworkCourseUpdate(List<String> coursework_id, Course course)
	{
		Set<CourseWork> listToDelete = new HashSet<>();
		
		if (coursework_id != null)
		{
			List<CourseWork> courseworkToRemove = new ArrayList<>();
			for (CourseWork cw : course.getCourse_works())
			{
				if (!coursework_id.contains(cw.getCoursework_id()))
					courseworkToRemove.add(cw);
			}
			
			for (CourseWork cw : courseworkToRemove)
			{
				course.getCourse_works().remove(cw);
				listToDelete.add(cw);
			}
			
			System.out.println(course.getCourse_works().size());
			
			for (String cwid : coursework_id)
			{
				CourseWork cw = cwservice.getById(Integer.parseInt(cwid));
				if (!course.getCourse_works().contains(cw))
				{
					course.getCourse_works().add(cw);
				}
			}
		}
		else
		{
			for (CourseWork cw : course.getCourse_works())
			{
				listToDelete.add(cw);
			}
			course.getCourse_works().clear();
		}
		
		return listToDelete;
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
		
		Course course = cservice.getById(course_code);
		course.setClass_level(class_level);
		course.setCourse_number(course_number);
		course.setSection(section);
		course.setCourse_name(course_name);
		
		course.setDepartment(dservice.getById(Integer.parseInt(department_id)));
		course.setInstructor(eservice.getById(Integer.parseInt(employee_id)));
		
		course.getCourse_code();			// Auto generate course_code in case other values are changed
		
		studentCourseUpdate(student_id, course);
		Set<CourseWork> toDelete = courseworkCourseUpdate(coursework_id, course);
		
		try { cservice.update(course); } // In case course_code is the same, do nothing for now
		catch (Exception e) {
			e.printStackTrace();
		}
		
		for (CourseWork cw : toDelete)
		{
			cwservice.delete(cw);
		}
		
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