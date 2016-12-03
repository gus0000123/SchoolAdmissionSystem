package dao.data;

import java.util.List;

import bean.data.Course;
import template.DAO;
import util.HibernateUtil;

public class CourseDAO implements DAO<Course>
{
	@Override
	public void insert(Course o) { HibernateUtil.insert(o); }

	@Override
	public void update(Course o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Course.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAll() { return (List<Course>) HibernateUtil.loadAll(Course.class); }

	@Override
	public Course getByPrimaryKey(int id) { return (Course) HibernateUtil.load(Course.class, id); }

	@Override
	public Course getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Course.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (Course) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static CourseDAO instance;
	
	private CourseDAO() { }
	public static CourseDAO getInstance()
	{
		if (instance == null) instance = new CourseDAO();
		return instance;
	}
}
