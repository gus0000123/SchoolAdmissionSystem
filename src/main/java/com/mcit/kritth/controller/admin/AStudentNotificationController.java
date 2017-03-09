package com.mcit.kritth.controller.admin;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;

@Controller
public class AStudentNotificationController {
	@Autowired
	private CourseBO cservice;
	@Autowired
	private UserBO uservice;
	
	private final String username = "mcit.fproj.test@gmail.com";
	private final String password = "fprojtest";
	
	@RequestMapping(value = "/course/notify/")
	public ModelAndView notifyStudents(@RequestParam("course_id") String course_id)
	{
		Course course = cservice.getById(course_id);
		
		for (Student student : course.getStudents())
		{
			try
			{
				User user = uservice.getByPersonId(student.getPerson().getID());
				sendMailTo(user);
			}
			catch (ObjectNotFoundException ex) { ex.printStackTrace(); }
		}
		
		String url = "forward:/course/view";
		
		ModelAndView model = new ModelAndView(url);
		
		return model;
	}
	
	private void sendMailTo(User user)
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mcit.fproj.test@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user.getPerson().getEmail()));
			message.setSubject("MCIT Update Notification");
			message.setText("Dear " + user.getPerson().getFullName() + ","
				+ "\n\nThis is automatic message to inform all students that there is new update to the mark in the course you participate. Please contact your instructor for more information."
				+ "\n\nRegards,\nMCIT IT Team");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			System.err.println("Error: E-mail was not successfully sent to " + user.getPerson().getEmail());
		}
	}
}
