package service.data;

import java.util.List;

import bean.data.Course;
import dao.data.CourseDAO;
import template.Service;

public class CourseService implements Service<Course>
{
	private CourseDAO dao;
	
	@Override
	public void insert(Course o) { dao.insert(o); }

	@Override
	public void update(Course o) { dao.update(o); }

	@Override
	public void delete(Course o) { dao.removeByPrimaryKey(o.getCourse_id()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Course getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Course> getAll() { return dao.getAll(); }

	// Singleton
	private static CourseService instance;
	public CourseService() { this.dao = CourseDAO.getInstance(); }
	
	public static CourseService getInstance()
	{
		if (instance == null) instance = new CourseService();
		return instance;
	}
}
