package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.StudentGradeBO;
import com.mcit.kritth.dao.template.StudentGradeDAO;
import com.mcit.kritth.model.data.StudentGrade;

@Service("studentGradeService")
@Transactional
public class StudentGradeBOImpl implements StudentGradeBO
{
	@Autowired
	private StudentGradeDAO dao;
	
	@Override
	public void insert(StudentGrade o) { dao.insertBean(o); }

	@Override
	public void update(StudentGrade o) { dao.updateBean(o); }

	@Override
	public void delete(StudentGrade o) { dao.removeBeanByPrimaryKey(o.getGradeId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public StudentGrade getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<StudentGrade> getAll() { return dao.getAllBeans(); }
}
