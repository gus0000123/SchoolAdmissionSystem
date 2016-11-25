package dao;

import java.util.List;

import beans.Student;
import hibernate.HibernateUtil;

public class StudentDAO implements DAO<Student>
{
	@Override
	public void insert(Student o) { HibernateUtil.insert(o); }

	@Override
	public void update(Student o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.delete(id); }

	@Override
	@SuppressWarnings("unchecked")
	public List<Student> getAll() { return (List<Student>) HibernateUtil.loadAll(Student.class); }

	@Override
	public Student getByPrimaryKey(int id) { return (Student) HibernateUtil.load(Student.class, id); }
	
	// Singleton
	private static StudentDAO instance;
	
	private StudentDAO() { }
	public static StudentDAO getInstance()
	{
		if (instance == null) instance = new StudentDAO();
		return instance;
	}
}
