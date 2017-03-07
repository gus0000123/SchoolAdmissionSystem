package com.mcit.kritth.controller.admin;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.CourseMarkWrapper;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.model.data.Student;

@Controller
public class ACourseMarkController
{
	@Autowired
	private CourseMarkBO cmservice;
	
	@Autowired
	private StudentBO sservice;
	
	@Autowired
	private CourseWorkBO cwservice;
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Student.class, "student", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text)
			{
				int id = Integer.parseInt(text);
				Student student = sservice.getById(id);
				setValue(student);
			}
		});
		
		binder.registerCustomEditor(CourseWork.class, "coursework", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text)
			{
				int id = Integer.parseInt(text);
				CourseWork coursework = cwservice.getById(id);
				setValue(coursework);
			}
		});
	}
	@RequestMapping(value = "/coursemark", method = RequestMethod.POST)
	public ModelAndView courseMarkContentSelector(
			@RequestParam(value = "actionPerformed", required = false) boolean performed,
			@RequestParam("mode") String mode)
	{
		String url = "forward:/student/view";
		
		switch(mode)
		{
			case "edit":
				if (!performed) url = "forward:/coursemark/edit";
				else url = "forward:/coursemark/edit/perform";
				break;
		}
		
		ModelAndView model = new ModelAndView(url);
		
		System.out.println("Go to " + url);
		
		return model;
	}
	
	@RequestMapping(value = "/coursemark/edit", method = RequestMethod.POST)
	public ModelAndView courseMarkStartEdit(
			@RequestParam("student_id") int student_id)
	{
		String url = "layout/adminApp";
		
		Student student = sservice.getById(student_id); 
		
		CourseMarkWrapper cmw = new CourseMarkWrapper();
		
		cmw.setList(new ArrayList<CourseMark>());
		for (CourseMark cm : student.getMarks())
		{
			cmw.getList().add(cm);
		}
		
		ModelAndView model = new ModelAndView(url);
		
		model.addObject("student", student);
		model.addObject("tab", "coursemark");
		model.addObject("mode", "edit");
		model.addObject("coursemarks", cmw);
		
		return model;
	}
	
	@RequestMapping(value = "/coursemark/edit/perform", method = RequestMethod.POST)
	public ModelAndView courseMarkDoEdit(
			@RequestParam("student_id") String id,
			@ModelAttribute("coursemarks") CourseMarkWrapper cmw)
	{
		String url = "forward:/student/view";
		Student student = sservice.getById(Integer.parseInt(id));
		
		if (cmw != null && cmw.getList().size() > 0)
		{
			for (CourseMark cm : cmw.getList())
			{
				System.out.println(cm.getCoursemark_id());
			}
		}
		
		ModelAndView model = new ModelAndView(url);

		return model;
	}	
}