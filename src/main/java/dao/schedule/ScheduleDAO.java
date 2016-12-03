package dao.schedule;

import java.util.List;

import bean.schedule.Schedule;
import template.DAO;
import util.HibernateUtil;

public class ScheduleDAO implements DAO<Schedule>
{
	@Override
	public void insert(Schedule o) { HibernateUtil.insert(o); }

	@Override
	public void update(Schedule o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Schedule.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getAll() { return (List<Schedule>) HibernateUtil.loadAll(Schedule.class); }

	@Override
	public Schedule getByPrimaryKey(int id) { return (Schedule) HibernateUtil.load(Schedule.class, id); }

	@Override
	public Schedule getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Schedule.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (Schedule) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static ScheduleDAO instance;
	
	private ScheduleDAO() { }
	public static ScheduleDAO getInstance()
	{
		if (instance == null) instance = new ScheduleDAO();
		return instance;
	}
}
