package dao.data;

import java.util.List;

import bean.data.Student;
import template.DAO;
import util.HibernateUtil;

public class StudentDAO implements DAO<Student>
{
	@Override
	public void insert(Student o) { HibernateUtil.insert(o); }

	@Override
	public void update(Student o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Student.class, id); }

	@Override
	@SuppressWarnings("unchecked")
	public List<Student> getAll() { return (List<Student>) HibernateUtil.loadAll(Student.class); }

	@Override
	public Student getByPrimaryKey(int id) { return (Student) HibernateUtil.load(Student.class, id); }
	
	@Override
	public Student getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(Student.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (Student) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static StudentDAO instance;
	
	private StudentDAO() { }
	public static StudentDAO getInstance()
	{
		if (instance == null) instance = new StudentDAO();
		return instance;
	}
}
