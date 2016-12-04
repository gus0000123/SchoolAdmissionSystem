package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.CourseWorkDAOImpl;
import com.mcit.kritth.model.data.CourseWork;

@Service("courseWorkService")
@Transactional
public class CourseWorkBOImpl implements BO<CourseWork>
{
	@Autowired
	private CourseWorkDAOImpl dao;
	
	@Override
	public void insert(CourseWork o) { dao.insertBean(o); }

	@Override
	public void update(CourseWork o) { dao.updateBean(o); }

	@Override
	public void delete(CourseWork o) { dao.removeBeanByPrimaryKey(o.getCoursework_id()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public CourseWork getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<CourseWork> getAll() { return dao.getAllBeans(); }
}
