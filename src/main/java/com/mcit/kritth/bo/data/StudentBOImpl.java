package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.StudentDAOImpl;
import com.mcit.kritth.model.data.Student;

@Service("studentService")
@Transactional
public class StudentBOImpl implements BO<Student>
{
	@Autowired
	private StudentDAOImpl dao;
	
	@Override
	public void insert(Student o) { dao.insertBean(o); }

	@Override
	public void update(Student o) { dao.updateBean(o); }

	@Override
	public void delete(Student o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Student getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Student> getAll() { return dao.getAllBeans(); }
}
