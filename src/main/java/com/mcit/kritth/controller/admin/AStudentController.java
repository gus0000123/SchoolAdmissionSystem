package com.mcit.kritth.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.bo.template.PersonBO;
import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Department;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentAdmissionStatus;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Controller
public class AStudentController
{
	private CourseBO cservice;
	private StudentBO sservice;
	private DepartmentBO dservice;
	private StudentAdmissionStatusBO saservice;
	private PersonBO pservice;
	
	private void init()
	{
		cservice = ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class);
		sservice = ApplicationContextProvider.getApplicationContext().getBean(StudentBO.class);
		dservice = ApplicationContextProvider.getApplicationContext().getBean(DepartmentBO.class);
		saservice = ApplicationContextProvider.getApplicationContext().getBean(StudentAdmissionStatusBO.class);
		pservice = ApplicationContextProvider.getApplicationContext().getBean(PersonBO.class);
	}
	
	@RequestMapping(value = "/studentController")
	public ModelAndView studentActionSelector(
			@RequestParam(value = "studentAction", required = false) String action)
	{
		if (cservice == null) init();
		
		String url = "forward:/studentView";
		
		if (action == null) action = "view";
		switch(action)
		{ 
			case "insert":
				url = "forward:/studentDoInsert";
				break;
			case "to_edit":
				url = "forward:/studentStartEdit";
				break;
			case "edit":
				url = "forward:/studentDoEdit";
				break;
			case "delete":
				url = "forward:/studentDoDelete";
				break;
			case "view":
			default:
				url = "forward:/studentView";
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	@RequestMapping(value = "/studentDoInsert")
	public ModelAndView studentInitInsert(
			@RequestParam("attachStudent") List<String> attachStudent,
			@RequestParam("id") String id,
			@RequestParam(value = "s_department") String department_id)
	{
		String url = "forward:/personView";
		
		if (attachStudent.size() > 0 && attachStudent.get(0).equals("attach"))
		{
			Student student = null;
			
			try
			{
				student = sservice.getById(Integer.parseInt(id));
				student.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				sservice.update(student);
			}
			catch (ObjectNotFoundException ex)
			{
				student = new Student();
				student.setAdmissionStatus(saservice.getById("Pending"));
				student.setPerson(pservice.getById(Integer.parseInt(id)));
				student.setDepartment(dservice.getById(Integer.parseInt(department_id)));
				sservice.insert(student);
			}
		}
		else
		{
			sservice.delete(sservice.getById(Integer.parseInt(id)));
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("tab", "person");
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "/studentDoDelete")
	public ModelAndView doDeleteStudent(
			@RequestParam("selection") List<String> selection)
	{
		String url = "forward:/studentView";
		
		for (String id : selection)
		{
			sservice.delete(sservice.getById(Integer.parseInt(id)));
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "/studentView")
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
		
		StudentAdmissionStatusBO saservice = ApplicationContextProvider.getApplicationContext().getBean("studentAdmissionStatusService", StudentAdmissionStatusBO.class);
		model.addObject("student_status_list", saservice.getAll());
		
		DepartmentBO dservice = ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class);
		model.addObject("department_list", dservice.getAll());
		
		CourseBO cservice = ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class);
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
