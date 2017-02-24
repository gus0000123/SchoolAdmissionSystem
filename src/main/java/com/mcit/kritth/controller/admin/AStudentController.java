package com.mcit.kritth.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
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
	
	@RequestMapping(value = "/student")
	public ModelAndView studentActionSelector(
			@RequestParam(value = "studentAction", required = false) String action)
	{
		String url = "forward:/student/view";
		
		if (action == null) action = "view";
		switch(action)
		{ 
			case "to_edit":
				url = "forward:/studentStartEdit";
				break;
			case "edit":
				url = "forward:/studentDoEdit";
				break;
			case "delete":
				url = "forward:/student/delete";
				break;
			case "view":
			default:
				url = "forward:/student/view";
				break;
		}
		
		System.out.println("Forward to " + url);
		
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
		else
		{
			url = "forward:/person/view";
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
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
	
	@RequestMapping(value = "/studentStartEdit")
	public ModelAndView studentStartEdit(
			@RequestParam("id") String sid)
	{
		String url = "layout/adminApp";
		
		int id = Integer.parseInt(sid);
		Student s = sservice.getById(id);
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("p_id", id);
		model.addObject("s_major", s.getMajor());
		model.addObject("s_minor", s.getMinor());
		model.addObject("s_start_date", s.getStartDate());
		model.addObject("student", s);
		model.addObject("student_status_list", saservice.getAll());
		model.addObject("department_list", dservice.getAll());
		model.addObject("all_courses", cservice.getAll());
		
		model.addObject("tab", "student");
		return model;
	}
	
	@RequestMapping(value = "/studentDoEdit")
	public ModelAndView studentDoEdit(
			@RequestParam("id") String id,
			@RequestParam("s_major") String major,
			@RequestParam("s_minor") String minor,
			@RequestParam("s_department") String department_id,
			@RequestParam("s_admission_status") String status,
			@RequestParam(value = "s_course_selection", required = false) List<String> courses)
	{
		String url = "forward:/studentView";
		
		int pid = Integer.parseInt(id);
		int did = Integer.parseInt(department_id);
		
		Student s = new Student();
		
		Department d = dservice.getById(did);
		StudentAdmissionStatus sa = saservice.getById(status);
		
		s.setId(pid);
		s.setMajor(major);
		s.setMinor(minor);
		s.setDepartment(d);
		s.setAdmissionStatus(sa);
		
		s.getEnrolled_courses().clear();
		
		if (courses != null)
		{
			for (String cid : courses)
			{
				Course c = cservice.getById(cid);
				s.getEnrolled_courses().add(c);
			}
		}
		
		sservice.update(s);
		
		ModelAndView model = new ModelAndView(url);
		
		model.addObject("tab", "student");
		return model;
	}
}
