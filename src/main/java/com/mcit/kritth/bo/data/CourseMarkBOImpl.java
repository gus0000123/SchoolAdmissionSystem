package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.CourseMarkDAOImpl;
import com.mcit.kritth.model.data.CourseMark;

@Service("courseMarkService")
@Transactional
public class CourseMarkBOImpl implements BO<CourseMark>
{
	@Autowired
	private CourseMarkDAOImpl dao;
	
	@Override
	public void insert(CourseMark o) { dao.insertBean(o); }

	@Override
	public void update(CourseMark o) { dao.updateBean(o); }

	@Override
	public void delete(CourseMark o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public CourseMark getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<CourseMark> getAll() { return dao.getAllBeans(); }
}
