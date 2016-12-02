package service.data;

import java.util.List;

import bean.data.StudentAdmissionStatus;
import dao.data.StudentAdmissionStatusDAO;
import template.Service;

public class StudentAdmissionStatusService implements Service<StudentAdmissionStatus>
{
	private StudentAdmissionStatusDAO dao;
	
	@Override
	public void insert(StudentAdmissionStatus o) { dao.insert(o); }

	@Override
	public void update(StudentAdmissionStatus o) { dao.update(o); }

	@Override
	public void delete(StudentAdmissionStatus o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public StudentAdmissionStatus getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<StudentAdmissionStatus> getAll() { return dao.getAll(); }
	
	@Override
	public StudentAdmissionStatus getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static StudentAdmissionStatusService instance;
	public StudentAdmissionStatusService() { this.dao = StudentAdmissionStatusDAO.getInstance(); }
	
	public static StudentAdmissionStatusService getInstance()
	{
		if (instance == null) instance = new StudentAdmissionStatusService();
		return instance;
	}
}
