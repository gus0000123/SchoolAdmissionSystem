package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.dao.template.CourseMarkDAO;
import com.mcit.kritth.model.data.CourseMark;

@Service("courseMarkService")
@Transactional
public class CourseMarkBOImpl implements CourseMarkBO
{
	@Autowired
	private CourseMarkDAO dao;
	
	@Override
	public void insert(CourseMark o) { dao.insertBean(o); }

	@Override
	public void update(CourseMark o)
	{
		CourseMark cm = getById(o.getCoursemark_id());
		cm.setCoursework(o.getCoursework());
		cm.setMark(o.getMark());
		cm.setStudent(o.getStudent());
		dao.updateBean(cm);
	}

	@Override
	public void delete(CourseMark o) { dao.removeBeanByPrimaryKey(o.getCoursemark_id()); }

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public CourseMark getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<CourseMark> getAll() { return dao.getAllBeans(); }
}
