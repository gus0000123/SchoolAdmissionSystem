package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.dao.data.CourseDAOImpl;
import com.mcit.kritth.model.data.Course;

@Service("courseService")
@Transactional
public class CourseBOImpl implements CourseBO
{
	@Autowired
	private CourseDAOImpl dao;
	
	@Override
	public void insert(Course o) { dao.insertBean(o); }

	@Override
	public void update(Course o) { dao.updateBean(o); }

	@Override
	public void delete(Course o) { dao.removeBeanByPrimaryKey(o.getCourse_code()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Course getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Course> getAll() { return dao.getAllBeans(); }
}
