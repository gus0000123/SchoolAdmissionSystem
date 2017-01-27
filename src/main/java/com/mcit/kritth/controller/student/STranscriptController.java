package com.mcit.kritth.controller.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.StudentGrade;
import com.mcit.kritth.model.data.User;

@Controller
@SessionAttributes("user")
public class STranscriptController
{
	@Autowired
	private StudentBO sservice;
	
	@RequestMapping(value = "/studentTranscript", method = RequestMethod.POST)
	public ModelAndView getOverviewContext(
			@ModelAttribute User u)
	{		
		Student student = sservice.getById(u.getPerson().getID());
		
		ModelAndView model = new ModelAndView("layout/studentApp");
		
		// Attach
		model.addObject("tab", "transcript");
		model.addObject("student", student);
		model.addObject("grades", getStudentGrades(student));
		model.addObject("letter", getLetterGrades(student));
		
		return model;
	}
	
	private List<StudentGrade> getStudentGrades(Student student)
	{
		ArrayList<StudentGrade> result = new ArrayList<>();
		result.addAll(student.getMarks());
		result.sort((o1, o2) -> o1.getStart_date().compareTo(o2.getStart_date()));
		
		return result;
	}
	
	private Map<String, String> getLetterGrades(Student student)
	{
		HashMap<String, String> result = new HashMap<>();
		
		for (StudentGrade sg : student.getMarks())
		{
			if (!sg.getCourse().isIs_active())
			{
				double total_mark = 0.0;
				for (CourseMark cm : sg.getCourseMarks())
				{
					total_mark += (double) cm.getMark() / cm.getCoursework().getMax_mark() * cm.getCoursework().getContribution() * 100;
				}
				
				String letter = "";
				
				if (total_mark > 85) letter = "A";
				else if (total_mark > 75) letter = "B";
				else if (total_mark > 65) letter = "C";
				else if (total_mark > 55) letter = "D";
				else letter = "F";
					
				result.put(sg.getCourse().getCourse_code(), letter);
			}
		}
		
		return result;
	}
}
