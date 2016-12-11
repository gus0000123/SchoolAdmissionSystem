package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.dao.template.ScheduleDAO;
import com.mcit.kritth.model.data.Schedule;

@SuppressWarnings("unchecked")
@Repository("scheduleDAO")
public class ScheduleDAOImpl extends HibernateSupport implements ScheduleDAO
{
	@Override
	public void insertBean(Schedule o) { insert(o); }

	@Override
	public void updateBean(Schedule o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Schedule.class, id); }

	@Override
	public List<Schedule> getAllBeans() { return (List<Schedule>) loadAll(Schedule.class); }

	@Override
	public Schedule getModelByPrimaryKey(Serializable id) { return (Schedule) load(Schedule.class, id); }
}
