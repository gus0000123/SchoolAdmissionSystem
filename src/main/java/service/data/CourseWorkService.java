package service.data;

import java.util.List;

import bean.data.CourseWork;
import dao.data.CourseWorkDAO;
import template.Service;

public class CourseWorkService implements Service<CourseWork>
{
	private CourseWorkDAO dao;
	
	@Override
	public void insert(CourseWork o) { dao.insert(o); }

	@Override
	public void update(CourseWork o) { dao.update(o); }

	@Override
	public void delete(CourseWork o) { dao.removeByPrimaryKey(o.getCoursework_id()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public CourseWork getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<CourseWork> getAll() { return dao.getAll(); }

	// Singleton
	private static CourseWorkService instance;
	public CourseWorkService() { this.dao = CourseWorkDAO.getInstance(); }
	
	public static CourseWorkService getInstance()
	{
		if (instance == null) instance = new CourseWorkService();
		return instance;
	}
}
