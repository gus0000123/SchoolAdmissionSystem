package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.StudentGrade;

@SuppressWarnings("unchecked")
@Repository("studentGradeDAO")
public class StudentGradeDAOImpl extends HibernateSupport implements DAO<StudentGrade>
{
	@Override
	public void insertBean(StudentGrade o) { insert(o); }

	@Override
	public void updateBean(StudentGrade o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(StudentGrade.class, id); }
	
	@Override
	public List<StudentGrade> getAllBeans() { return (List<StudentGrade>) loadAll(StudentGrade.class); }

	@Override
	public StudentGrade getModelByPrimaryKey(Serializable id) { return (StudentGrade) load(StudentGrade.class, id); }
}
