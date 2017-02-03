package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.StudentAdmissionStatusBO;
import com.mcit.kritth.dao.template.StudentAdmissionStatusDAO;
import com.mcit.kritth.model.data.StudentAdmissionStatus;

@Service("studentAdmissionStatusService")
@Transactional
public class StudentAdmissionStatusBOImpl implements StudentAdmissionStatusBO
{
	@Autowired
	private StudentAdmissionStatusDAO dao;
	
	@Override
	public void insert(StudentAdmissionStatus o) { dao.insertBean(o); }

	@Override
	public void update(StudentAdmissionStatus o) { dao.updateBean(o); }

	@Override
	public void delete(StudentAdmissionStatus o) { dao.removeBeanByPrimaryKey(o.getStatus()); }

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public StudentAdmissionStatus getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<StudentAdmissionStatus> getAll() { return dao.getAllBeans(); }
}
