package com.mcit.kritth.controller.admin;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentAdmissionStatus;

@Controller
public class AStudentController
{
	@Autowired
	private CourseBO cservice;
	@Autowired
	private StudentBO sservice;
	@Autowired
	private DepartmentBO dservice;
	@Autowired
	private StudentAdmissionStatusBO saservice;
	@Autowired
	private PersonBO pservice;
	@Autowired
	private CourseMarkBO cmservice;
	
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
		binder.registerCustomEditor(StudentAdmissionStatus.class, "admission_status", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text)
			{
				setValue(saservice.getById(text));
			}
		});
		binder.registerCustomEditor(Person.class, "person", new PropertyEditorSupport() {
			@Override
			public String getAsText()
			{
				Person p = (Person) this.getValue();
				return "" + p.getID();
			}
			
			@Override
			public void setAsText(String text)
			{
				setValue(pservice.getById(Integer.parseInt(text)));
			}
		});
		binder.registerCustomEditor(Set.class, "enrolled_courses", new PropertyEditorSupport() {
			@SuppressWarnings("unchecked")
			@Override
			public String getAsText()
			{
				Set<Course> courses = (Set<Course>) this.getValue();
				String result = "";
				for (Course c : courses)
				{
					result += c.getCourse_code() + ",";
				}
				return result;
			}
			
			@Override
			public void setAsText(String text)
			{
				String[] list = text.split(",");
				Set<Course> courses = new HashSet<>();
				for (int i = 0; i < list.length; i++)
				{
					courses.add(cservice.getById(list[i]));
				}
				setValue(courses);
			}
		});
		binder.registerCustomEditor(Set.class, "marks", new PropertyEditorSupport() {
			@SuppressWarnings("unchecked")
			@Override
			public String getAsText()
			{
				Set<CourseMark> cms = (Set<CourseMark>) this.getValue();
				String result = "";
				for (CourseMark cm : cms)
				{
					result += cm.getCoursemark_id() + ",";
				}
				return result;
			}
			@Override
			public void setAsText(String text)
			{
				String[] list = text.split(",");
				Set<CourseMark> cms = new HashSet<>();
				for (int i = 0; i < list.length; i++)
				{
					cms.add(cmservice.getById(Integer.parseInt(list[i])));
				}
				setValue(cms);
			}
		});
	}
	
	@RequestMapping(value = "/student")
	public ModelAndView studentActionSelector(
			@RequestParam(value = "studentAction", required = false) String action)
	{
		String url = "forward:/student/view";
		
		if (action == null) action = "view";
		switch(action)
		{ 
			case "to_edit":
				url = "forward:/student/edit";
				break;
			case "edit":
				url = "forward:/student/edit/perform";
				break;
			case "delete":
				url = "forward:/student/delete";
				break;
			case "view":
			default:
				url = "forward:/student/view";
				break;
		}
		
		System.out.println("Go to " + url);
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/student/delete")
	public ModelAndView doDeleteStudent(
			@RequestParam(value = "selection", required = false) List<String> selection) throws Exception
	{
		String url = "forward:/student/view";
		
		// Delete if it does not exist
		if (selection != null)
		{
			for (String id : selection)
			{
				sservice.delete(sservice.getById(Integer.parseInt(id)));
			}
		}
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/student/view")
	public ModelAndView studentList()
	{
		String url = "layout/adminApp";
		
		List<Student> list = sservice.getAll();
		
		if (list == null) { list = new ArrayList<>(); }
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "student");
		model.addObject("mode", "view");
		model.addObject("list", list);
		
		return model;
	}
	
	@RequestMapping(value = "/student/edit")
	public ModelAndView studentStartEdit(
			@RequestParam("id") String sid)
	{
		String url = "layout/adminApp";
		
		int id = Integer.parseInt(sid);
		Student s = sservice.getById(id);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("student", s);
		model.addObject("student_status_list", saservice.getAll());
		model.addObject("department_list", dservice.getAll());
		model.addObject("all_courses", cservice.getAll());
		model.addObject("all_marks", s.getMarks().toArray(new CourseMark[s.getMarks().size()]));
		model.addObject("tab", "student");
		return model;
	}
	
	@RequestMapping(value = "/student/edit/perform", method = RequestMethod.POST)
	public ModelAndView studentDoEdit(
			@ModelAttribute("student") Student student,
			BindingResult result)
	{
		String url = "forward:/student/view";
		sservice.update(student);
		
		ModelAndView model = new ModelAndView(url);
		return model;
	}
}
