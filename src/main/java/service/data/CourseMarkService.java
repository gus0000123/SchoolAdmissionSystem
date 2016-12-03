package service.data;

import java.util.List;

import bean.data.CourseMark;
import dao.data.CourseMarkDAO;
import template.Service;

public class CourseMarkService implements Service<CourseMark>
{
	private CourseMarkDAO dao;
	
	@Override
	public void insert(CourseMark o) { dao.insert(o); }

	@Override
	public void update(CourseMark o) { dao.update(o); }

	@Override
	public void delete(CourseMark o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public CourseMark getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<CourseMark> getAll() { return dao.getAll(); }
	
	// Singleton
	private static CourseMarkService instance;
	public CourseMarkService() { this.dao = CourseMarkDAO.getInstance(); }
	
	public static CourseMarkService getInstance()
	{
		if (instance == null) instance = new CourseMarkService();
		return instance;
	}
}
