package dao.data;

import java.util.List;

import bean.data.StudentGrade;
import template.DAO;
import util.HibernateUtil;

public class StudentGradeDAO implements DAO<StudentGrade>
{
	@Override
	public void insert(StudentGrade o) { HibernateUtil.insert(o); }

	@Override
	public void update(StudentGrade o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(StudentGrade.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentGrade> getAll() { return (List<StudentGrade>) HibernateUtil.loadAll(StudentGrade.class); }

	@Override
	public StudentGrade getByPrimaryKey(int id) { return (StudentGrade) HibernateUtil.load(StudentGrade.class, id); }
	
	// Singleton
	private static StudentGradeDAO instance;
	
	private StudentGradeDAO() { }
	public static StudentGradeDAO getInstance()
	{
		if (instance == null) instance = new StudentGradeDAO();
		return instance;
	}
}
