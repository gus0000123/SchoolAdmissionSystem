package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.Department;

@SuppressWarnings("unchecked")
@Repository("departmentDAO")
public class DepartmentDAOImpl extends HibernateSupport implements DAO<Department>
{
	@Override
	public void insertBean(Department o) { insert(o); }

	@Override
	public void updateBean(Department o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Department.class, id); }

	@Override
	public List<Department> getAllBeans() { return (List<Department>) loadAll(Department.class); }

	@Override
	public Department getModelByPrimaryKey(Serializable id) { return (Department) load(Department.class, id); }
}
