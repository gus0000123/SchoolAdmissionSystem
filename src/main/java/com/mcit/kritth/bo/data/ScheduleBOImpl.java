package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.ScheduleDAOImpl;
import com.mcit.kritth.model.data.Schedule;

@Service("scheduleService")
@Transactional
public class ScheduleBOImpl implements BO<Schedule>
{
	@Autowired
	private ScheduleDAOImpl dao;
	
	@Override
	public void insert(Schedule o) { dao.insertBean(o); }

	@Override
	public void update(Schedule o) { dao.updateBean(o); }

	@Override
	public void delete(Schedule o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Schedule getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Schedule> getAll() { return dao.getAllBeans(); }
}
