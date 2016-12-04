package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.StudentAdmissionStatusDAOImpl;
import com.mcit.kritth.model.data.StudentAdmissionStatus;

@Service("studentAdmissionStatusService")
@Transactional
public class StudentAdmissionStatusBOImpl implements BO<StudentAdmissionStatus>
{
	@Autowired
	private StudentAdmissionStatusDAOImpl dao;
	
	@Override
	public void insert(StudentAdmissionStatus o) { dao.insertBean(o); }

	@Override
	public void update(StudentAdmissionStatus o) { dao.updateBean(o); }

	@Override
	public void delete(StudentAdmissionStatus o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public StudentAdmissionStatus getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<StudentAdmissionStatus> getAll() { return dao.getAllBeans(); }
}
