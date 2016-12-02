package service.schedule;

import java.util.List;

import bean.schedule.Schedule;
import dao.schedule.ScheduleDAO;
import template.Service;

public class ScheduleService implements Service<Schedule>
{
	private ScheduleDAO dao;
	
	@Override
	public void insert(Schedule o) { dao.insert(o); }

	@Override
	public void update(Schedule o) { dao.update(o); }

	@Override
	public void delete(Schedule o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Schedule getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Schedule> getAll() { return dao.getAll(); }
	
	@Override
	public Schedule getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static ScheduleService instance;
	public ScheduleService() { this.dao = ScheduleDAO.getInstance(); }
	
	public static ScheduleService getInstance()
	{
		if (instance == null) instance = new ScheduleService();
		return instance;
	}
}
