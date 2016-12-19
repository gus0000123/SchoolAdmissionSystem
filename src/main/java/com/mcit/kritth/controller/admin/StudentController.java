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
public class StudentController
{
	@RequestMapping(value = "/studentController")
	public ModelAndView studentActionSelector(
			@RequestParam(value = "studentAction", required = false) String action)
	{
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
		model.addObject("tab", "person");
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "/studentDoDelete")
	public ModelAndView doDeleteStudent(
			@RequestParam("selection") List<String> selection)
	{
		String url = "forward:/studentView";
		
		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		for (String id : selection)
		{
			service.deleteById(Integer.parseInt(id));
		}
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("mode", "view");
		
		return model;
	}
	
	@RequestMapping(value = "/studentView")
	public ModelAndView studentList()
	{
		String url = "layout/adminApp";
		
		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		List<Student> list = service.getAll();
		
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
		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		Student s = service.getById(id);
		
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
			@RequestParam("s_department") String department_id,
			@RequestParam("s_admission_status") String status,
			@RequestParam(value = "s_course_selection", required = false) List<String> courses)
	{
		String url = "forward:/studentView";
		
		int pid = Integer.parseInt(id);
		int did = Integer.parseInt(department_id);
		
		StudentBO service = ApplicationContextProvider.getApplicationContext().getBean("studentService", StudentBO.class);
		Student s = service.getById(pid);
		
		DepartmentBO dservice = ApplicationContextProvider.getApplicationContext().getBean("departmentService", DepartmentBO.class);
		Department d = dservice.getById(did);
		
		StudentAdmissionStatusBO saservice = ApplicationContextProvider.getApplicationContext().getBean("studentAdmissionStatusService", StudentAdmissionStatusBO.class);
		StudentAdmissionStatus sa = saservice.getById(status);
		
		s.getEnrolled_courses().clear();
		if (courses != null)
		{
			CourseBO cservice = ApplicationContextProvider.getApplicationContext().getBean("courseService", CourseBO.class);
			ArrayList<String> codes = new ArrayList<>();
			for (String cid : courses)
			{
				if (!codes.contains(cid))
				{
					codes.add(cid);
					Course c = cservice.getById(cid);
					s.getEnrolled_courses().add(c);
				}
			}
		}
		
		s.setDepartment(d);
		s.setAdmissionStatus(sa);
		service.update(s);
		
		ModelAndView model = new ModelAndView(url);
		
		model.addObject("tab", "student");
		return model;
	}
}
