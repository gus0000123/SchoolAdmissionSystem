package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.StudentAdmissionStatus;

@SuppressWarnings("unchecked")
@Repository("studentAdmissionStatusDAO")
public class StudentAdmissionStatusDAOImpl extends HibernateSupport implements DAO<StudentAdmissionStatus> {

	@Override
	public void insertBean(StudentAdmissionStatus o) { insert(o); }

	@Override
	public void updateBean(StudentAdmissionStatus o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(StudentAdmissionStatus.class, id); }
	
	@Override
	public List<StudentAdmissionStatus> getAllBeans() { return (List<StudentAdmissionStatus>) loadAll(StudentAdmissionStatus.class); }

	@Override
	public StudentAdmissionStatus getModelByPrimaryKey(Serializable id) { return (StudentAdmissionStatus) load(StudentAdmissionStatus.class, id); }
}
