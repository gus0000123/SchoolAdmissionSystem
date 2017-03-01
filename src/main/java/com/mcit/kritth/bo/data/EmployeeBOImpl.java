package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.dao.template.EmployeeDAO;
import com.mcit.kritth.model.data.Employee;

@Service("employeeService")
@Transactional
public class EmployeeBOImpl implements EmployeeBO
{
	@Autowired
	private EmployeeDAO dao;
	
	@Override
	public void insert(Employee o) { dao.insertBean(o); }

	@Override
	public void update(Employee o)
	{
		Employee e = getById(o.getId());
		
		e.setSalary(o.getSalary());
		e.setRank(o.getRank());
		e.setPerson(o.getPerson());
		e.setDepartment(o.getDepartment());
		
		dao.updateBean(e);
	}

	@Override
	public void delete(Employee o) throws Exception
	{
		if (o.getAssigned_courses() == null || o.getAssigned_courses().size() == 0)
		{			
			dao.removeBeanByPrimaryKey(o.getId());
		}
		else
		{
			throw new Exception("Must remove this employee from all courses first");
		}
	}

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public Employee getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Employee> getAll() { return dao.getAllBeans(); }
}
