package service.data;

import java.util.List;

import bean.data.StudentGrade;
import dao.data.StudentGradeDAO;
import template.Service;

public class StudentGradeService implements Service<StudentGrade>
{
	private StudentGradeDAO dao;
	
	@Override
	public void insert(StudentGrade o) { dao.insert(o); }

	@Override
	public void update(StudentGrade o) { dao.update(o); }

	@Override
	public void delete(StudentGrade o) { dao.removeByPrimaryKey(o.getGradeId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public StudentGrade getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<StudentGrade> getAll() { return dao.getAll(); }
	
	// Singleton
	private static StudentGradeService instance;
	public StudentGradeService() { this.dao = StudentGradeDAO.getInstance(); }
	
	public static StudentGradeService getInstance()
	{
		if (instance == null) instance = new StudentGradeService();
		return instance;
	}
}
