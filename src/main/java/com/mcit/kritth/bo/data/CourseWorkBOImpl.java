package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.dao.template.CourseWorkDAO;
import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;
import com.mcit.kritth.spring.ApplicationContextProvider;

@Service("courseWorkService")
@Transactional
public class CourseWorkBOImpl implements CourseWorkBO
{
	@Autowired
	private CourseWorkDAO dao;
	
	@Override
	public void insert(CourseWork o) { dao.insertBean(o); }

	@Override
	public void update(CourseWork o) { dao.updateBean(o); }

	@Override
	public void delete(CourseWork o) { dao.removeBeanByPrimaryKey(o.getCoursework_id()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public CourseWork getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<CourseWork> getAll() { return dao.getAllBeans(); }

	@Override
	public boolean updateCourse(CourseWork obj, Course objToAdd)
	{
		/*
		CourseBO cservice = ApplicationContextProvider.getApplicationContext().getBean(CourseBO.class);
		
		if (objToAdd != null)
		{
			if (obj.getCourse().equals(objToAdd))
			{
				if (objToAdd.getCourse_works().contains(obj)) cservice.updateCourseWork(objToAdd, obj);
				obj.setCourse(null);
				delete(obj);
				return true;
			}
		}
		else
		{
			return false; //return updateCourseList(obj, null);
		}
		*/
		return false;
	}
}
