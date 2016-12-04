package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.CourseWork;

@SuppressWarnings("unchecked")
@Repository("courseWorkDAO")
public class CourseWorkDAOImpl extends HibernateSupport implements DAO<CourseWork>
{
	@Override
	public void insertBean(CourseWork o) { insert(o); }

	@Override
	public void updateBean(CourseWork o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(CourseWork.class, id); }

	@Override
	public List<CourseWork> getAllBeans() { return (List<CourseWork>) loadAll(CourseWork.class); }

	@Override
	public CourseWork getModelByPrimaryKey(Serializable id) { return (CourseWork) load(CourseWork.class, id); }
}
