package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.CourseDAO;
import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.model.data.Course;

@SuppressWarnings("unchecked")
@Repository("courseDAO")
public class CourseDAOImpl extends HibernateSupport implements CourseDAO
{
	@Override
	public void insertBean(Course o) { insert(o); }

	@Override
	public void updateBean(Course o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Course.class, id); }

	@Override
	public List<Course> getAllBeans() { return (List<Course>) loadAll(Course.class); }

	@Override
	public Course getModelByPrimaryKey(Serializable id) { return (Course) load(Course.class, id); }
}
