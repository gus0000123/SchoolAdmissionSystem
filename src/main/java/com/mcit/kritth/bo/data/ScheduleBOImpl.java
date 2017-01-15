package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.ScheduleBO;
import com.mcit.kritth.dao.template.ScheduleDAO;
import com.mcit.kritth.model.data.Schedule;

@Service("scheduleService")
@Transactional
public class ScheduleBOImpl implements ScheduleBO
{
	@Autowired
	private ScheduleDAO dao;
	
	@Override
	public void insert(Schedule o) { dao.insertBean(o); }

	@Override
	public void update(Schedule o) { dao.updateBean(o); }

	@Override
	public void delete(Schedule o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public Schedule getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Schedule> getAll() { return dao.getAllBeans(); }
}
