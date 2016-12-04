package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.StudentGradeDAOImpl;
import com.mcit.kritth.model.data.StudentGrade;

@Service("studentGradeService")
@Transactional
public class StudentGradeBOImpl implements BO<StudentGrade>
{
	@Autowired
	private StudentGradeDAOImpl dao;
	
	@Override
	public void insert(StudentGrade o) { dao.insertBean(o); }

	@Override
	public void update(StudentGrade o) { dao.updateBean(o); }

	@Override
	public void delete(StudentGrade o) { dao.removeBeanByPrimaryKey(o.getGradeId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public StudentGrade getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<StudentGrade> getAll() { return dao.getAllBeans(); }
}
