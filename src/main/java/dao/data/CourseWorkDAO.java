package dao.data;

import java.util.List;

import bean.data.CourseWork;
import template.DAO;
import util.HibernateUtil;

public class CourseWorkDAO implements DAO<CourseWork>
{
	@Override
	public void insert(CourseWork o) { HibernateUtil.insert(o); }

	@Override
	public void update(CourseWork o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(CourseWork.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseWork> getAll() { return (List<CourseWork>) HibernateUtil.loadAll(CourseWork.class); }

	@Override
	public CourseWork getByPrimaryKey(int id) { return (CourseWork) HibernateUtil.load(CourseWork.class, id); }

	@Override
	public CourseWork getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(CourseWork.class, "coursework_id", 1, true);
		if (result != null && result.size() > 0)
			return (CourseWork) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static CourseWorkDAO instance;
	
	private CourseWorkDAO() { }
	public static CourseWorkDAO getInstance()
	{
		if (instance == null) instance = new CourseWorkDAO();
		return instance;
	}
}
