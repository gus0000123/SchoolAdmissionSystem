package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.CourseBO;
import com.mcit.kritth.bo.template.CourseMarkBO;
import com.mcit.kritth.bo.template.CourseWorkBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.dao.template.CourseWorkDAO;
import com.mcit.kritth.model.data.CourseMark;
import com.mcit.kritth.model.data.CourseWork;

@Service("courseWorkService")
@Transactional
public class CourseWorkBOImpl implements CourseWorkBO
{
	@Autowired
	private CourseWorkDAO dao;
	
	@Autowired
	private CourseMarkBO cmservice;
	
	@Autowired
	private CourseBO cservice;
	
	@Autowired
	private StudentBO sservice;
	
	@Override
	public void insert(CourseWork o)
	{
		dao.insertBean(o);
		o.getCourse().getCourse_works().add(o);
		cservice.update(o.getCourse());
	}

	@Override
	public void update(CourseWork o)
	{
		CourseWork cw = getById(o.getCoursework_id());
		
		cw.setCoursework_name(o.getCoursework_name());
		cw.setCoursework_description(o.getCoursework_description());
		cw.setContribution(o.getContribution());
		cw.setMax_mark(o.getMax_mark());
		cw.setCourse(o.getCourse());
		
		dao.updateBean(cw);
	}

	@Override
	public void delete(CourseWork o)
	{
		// Can optimize by making specialized query but for now, will  just run through loop
		List<CourseMark> cmlist = cmservice.getAll();
		for (CourseMark cm : cmlist)
		{
			if (cm.getCoursework().equals(o))
			{
				try
				{
					cm.getStudent().getMarks().remove(cm);
					sservice.update(cm.getStudent());
					cmservice.delete(cm);
				} 
				catch (Exception e) { e.printStackTrace(); }
			}
		}
		
		dao.removeBeanByPrimaryKey(o.getCoursework_id());
	}

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public CourseWork getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<CourseWork> getAll() { return dao.getAllBeans(); }
}
