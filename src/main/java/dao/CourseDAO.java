package dao;

import java.util.List;

import beans.Course;
import hibernate.HibernateUtil;

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

	// Singleton
	private static CourseDAO instance;
	
	private CourseDAO() { }
	public static CourseDAO getInstance()
	{
		if (instance == null) instance = new CourseDAO();
		return instance;
	}
}
