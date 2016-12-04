package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.Employee;

@SuppressWarnings("unchecked")
@Repository("employeeDAO")
public class EmployeeDAOImpl extends HibernateSupport implements DAO<Employee>
{
	@Override
	public void insertBean(Employee o) { insert(o); }

	@Override
	public void updateBean(Employee o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Employee.class, id); }

	@Override
	public List<Employee> getAllBeans() { return (List<Employee>) loadAll(Employee.class); }

	@Override
	public Employee getModelByPrimaryKey(Serializable id) { return (Employee) load(Employee.class, id); }
}
