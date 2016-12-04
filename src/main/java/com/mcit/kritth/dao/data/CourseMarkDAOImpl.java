package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.CourseMark;

@SuppressWarnings("unchecked")
@Repository("courseMarkDAO")
public class CourseMarkDAOImpl extends HibernateSupport implements DAO<CourseMark>
{
	@Override
	public void insertBean(CourseMark o) { insert(o); }

	@Override
	public void updateBean(CourseMark o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(CourseMark.class, id); }

	@Override
	public List<CourseMark> getAllBeans() { return (List<CourseMark>) loadAll(CourseMark.class); }

	@Override
	public CourseMark getModelByPrimaryKey(Serializable id) { return (CourseMark) load(CourseMark.class, id); }
}
