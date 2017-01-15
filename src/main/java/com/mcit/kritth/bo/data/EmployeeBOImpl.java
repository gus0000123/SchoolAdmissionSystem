package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.dao.template.EmployeeDAO;
import com.mcit.kritth.model.data.Course;
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
	public void update(Employee o) { dao.updateBean(o); }

	@Override
	public void delete(Employee o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public Employee getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Employee> getAll() { return dao.getAllBeans(); }

	@Override
	public boolean updateAssignedCourse(Employee obj, Course objToAdd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAssignedCourseList(Employee obj, Set<Course> objToAdd) {
		// TODO Auto-generated method stub
		return false;
	}
}
