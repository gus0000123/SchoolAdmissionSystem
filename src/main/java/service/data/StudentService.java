package service.data;

import java.util.List;

import bean.data.Student;
import dao.data.StudentDAO;
import template.Service;

public class StudentService implements Service<Student>
{
	private StudentDAO dao;
	
	@Override
	public void insert(Student o) { dao.insert(o); }

	@Override
	public void update(Student o) { dao.update(o); }

	@Override
	public void delete(Student o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Student getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAll(); }
	
	// Singleton
	private static StudentService instance;
	public StudentService() { this.dao = StudentDAO.getInstance(); }
	
	public static StudentService getInstance()
	{
		if (instance == null) instance = new StudentService();
		return instance;
	}
}
