package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.Student;

@SuppressWarnings("unchecked")
@Repository("studentDAO")
public class StudentDAOImpl extends HibernateSupport implements DAO<Student>
{
	@Override
	public void insertBean(Student o) { insert(o); }

	@Override
	public void updateBean(Student o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Student.class, id); }

	@Override
	public List<Student> getAllBeans() { return (List<Student>) loadAll(Student.class); }

	@Override
	public Student getModelByPrimaryKey(Serializable id) { return (Student) load(Student.class, id); }
}
