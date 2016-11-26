package dao;

import java.util.List;

import beans.CourseMark;
import hibernate.HibernateUtil;

public class CourseMarkDAO implements DAO<CourseMark>
{
	@Override
	public void insert(CourseMark o) { HibernateUtil.insert(o); }

	@Override
	public void update(CourseMark o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(CourseMark.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseMark> getAll() { return (List<CourseMark>) HibernateUtil.loadAll(CourseMark.class); }

	@Override
	public CourseMark getByPrimaryKey(int id) { return (CourseMark) HibernateUtil.load(CourseMark.class, id); }

	// Singleton
	private static CourseMarkDAO instance;
	
	private CourseMarkDAO() { }
	public static CourseMarkDAO getInstance()
	{
		if (instance == null) instance = new CourseMarkDAO();
		return instance;
	}
}
