package com.mcit.kritth.bo.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.dao.template.CourseWorkDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestCourseWorkBO implements TestService
{
	@Mock
	private CourseWork instance;
	@Mock
	private Course course;
	@Mock
	private CourseMark coursemark;
	@Mock
	private Set<CourseWork> coursework_set;
	@Mock
	private List<CourseMark> coursemark_list;
	@Mock
	private CourseWorkDAO dao;
	@Mock
	private CourseBO cservice;
	@Mock
	private CourseMarkBO cmservice;
	@InjectMocks
	private CourseWorkBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Override
	public void testInsert()
	{
		when(instance.getCourse()).thenReturn(course);
		when(course.getCourse_works()).thenReturn(coursework_set);
		service.insert(instance);
		verify(dao).insertBean(instance);
		verify(coursework_set).add(instance);
		verify(cservice).update(course);	}

	@Test
	@Override
	public void testUpdate() {
		when(service.getById(0)).thenReturn(instance);
		service.update(instance);
		verify(dao).updateBean(instance);
	}

	@Test
	@Override
	public void testGet() {
		int id = TestUtil.generateRandomNumber();
		service.getById(id);
		verify(dao).getModelByPrimaryKey(id);
	}

	@Test
	@Override
	public void testGetAll() {
		service.getAll();
		verify(dao).getAllBeans();
	}

	@Test
	@Override
	public void testDelete() throws Exception {
		int id = TestUtil.generateRandomNumber();
		when(instance.getCoursework_id()).thenReturn(id);
		coursemark_list = new ArrayList<>();
		coursemark_list.add(coursemark);
		when(cmservice.getAll()).thenReturn(coursemark_list);
		when(coursemark.getCoursework()).thenReturn(instance);
		service.delete(instance);
		verify(cmservice).delete(coursemark);
		verify(dao).removeBeanByPrimaryKey(instance.getCoursework_id());
	}
	
}
