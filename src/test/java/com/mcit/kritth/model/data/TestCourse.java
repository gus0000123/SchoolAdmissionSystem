package com.mcit.kritth.model.data;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.mcit.kritth.model.TestBean;
import com.mcit.kritth.util.BeanUtil;
import com.mcit.kritth.util.TestUtil;

public class TestCourse implements TestBean
{
	private Course course;
	private String course_code;
	private int class_level;
	private int course_number;
	private int section;
	private String course_name;
	private String course_description;
	private int capacity;
	private int credit;
	private boolean is_active;
	private Department department;
	private Employee instructor;
	private CourseWork course_work;
	private Course prerequisite;
	private Employee ta;
	private Student student;

	@Before
	@Override
	public void init()
	{
		course = new Course();
		class_level = TestUtil.generateRandomNumber(8);
		course_number = TestUtil.generateRandomNumber(99);
		section = TestUtil.generateRandomNumber(9);
		course_name = TestUtil.generateRandomString();
		course_description = TestUtil.generateRandomString();
		capacity = TestUtil.generateRandomNumber(1000);
		credit = TestUtil.generateRandomNumber(120);
		is_active = true;
		department = TestUtil.createDummyDepartmentWithCode();
		instructor = new Employee();
		course_work = new CourseWork();
		prerequisite = new Course();
		ta = new Employee();
		student = new Student();
	}

	@Test
	@Override
	public void testGetterSetter()
	{
		// Setting
		course.setClass_level(class_level);
		course.setCourse_number(course_number);
		course.setSection(section);
		course.setCourse_name(course_name);
		course.setCourse_description(course_description);
		course.setCapacity(capacity);
		course.setCredit(credit);
		course.setIs_active(is_active);
		course.setDepartment(department);
		course.setInstructor(instructor);
		course.setCourse_works(new HashSet<CourseWork>());
		course.setPrerequisite(new HashSet<Course>());
		course.setTa(new HashSet<Employee>());
		course.setStudents(new HashSet<Student>());
				
		course_code = BeanUtil.getCourseCode(course);
		course.setCourse_code(course_code);
		
		assertNotNull(course);
		assertNotNull(course.getCourse_code());
		assertNotNull(course.getClass_level());
		assertNotNull(course.getCourse_number());
		assertNotNull(course.getSection());
		assertNotNull(course.getCourse_name());
		assertNotNull(course.getCourse_description());
		assertNotNull(course.getCapacity());
		assertNotNull(course.getCredit());
		assertNotNull(course.isIs_active());
		assertNotNull(course.getDepartment());
		assertNotNull(course.getInstructor());
		assertNotNull(course.getCourse_works());
		assertNotNull(course.getPrerequisite());
		assertNotNull(course.getTa());
		assertNotNull(course.getStudents());
		assertNotNull(course.toString());
		
		// Special get by getter
		course = new Course();
		course.getCourse_works().add(course_work);
		course.getPrerequisite().add(prerequisite);
		course.getTa().add(ta);
		course.getStudents().add(student);
		
		assertNotNull(course.getCourse_works());
		assertNotNull(course.getPrerequisite());
		assertNotNull(course.getTa());
		assertNotNull(course.getStudents());
	}
}