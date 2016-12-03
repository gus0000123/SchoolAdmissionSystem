package dao.data;

import java.util.List;

import bean.data.StudentAdmissionStatus;
import template.DAO;
import util.HibernateUtil;

public class StudentAdmissionStatusDAO implements DAO<StudentAdmissionStatus> {

	@Override
	public void insert(StudentAdmissionStatus o) { HibernateUtil.insert(o); }

	@Override
	public void update(StudentAdmissionStatus o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(StudentAdmissionStatus.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentAdmissionStatus> getAll() { return (List<StudentAdmissionStatus>) HibernateUtil.loadAll(StudentAdmissionStatus.class); }

	@Override
	public StudentAdmissionStatus getByPrimaryKey(int id) { return (StudentAdmissionStatus) HibernateUtil.load(StudentAdmissionStatus.class, id); }
	
	@Override
	public StudentAdmissionStatus getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowOrderByColumn(StudentAdmissionStatus.class, "id", 1, true);
		if (result != null && result.size() > 0)
			return (StudentAdmissionStatus) result.get(0);
		else
			return null;
	}
	
	// Single ton
	private static StudentAdmissionStatusDAO instance;
	
	private StudentAdmissionStatusDAO() { }
	public static StudentAdmissionStatusDAO getInstance()
	{
		if (instance == null)
			instance = new StudentAdmissionStatusDAO();
		return instance;
	}
}
