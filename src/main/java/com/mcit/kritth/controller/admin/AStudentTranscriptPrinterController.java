package com.mcit.kritth.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.Student;

@Controller
public class AStudentTranscriptPrinterController
{
	@Autowired
	private StudentBO sservice;
	
	@RequestMapping(value = "/student/print/{id}")
	public ModelAndView printTranscript(@PathVariable("id") int student_id)
	{
		Student student = sservice.getById(student_id);
		printTranscript(student);
		
		String url = "forward:/student/edit";
		
		ModelAndView model = new ModelAndView(url);
		model.addObject("id", student_id);
		return model;
	}
	
	private void printTranscript(Student student)
	{
		
	}
}
