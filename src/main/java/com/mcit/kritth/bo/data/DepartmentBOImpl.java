package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.DepartmentBO;
import com.mcit.kritth.dao.data.DepartmentDAOImpl;
import com.mcit.kritth.model.data.Department;

@Service("departmentService")
@Transactional
public class DepartmentBOImpl implements DepartmentBO
{
	@Autowired
	private DepartmentDAOImpl dao;
	
	@Override
	public void insert(Department o) { dao.insertBean(o); }

	@Override
	public void update(Department o) { dao.updateBean(o); }

	@Override
	public void delete(Department o) { dao.removeBeanByPrimaryKey(o.getDeptId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Department getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Department> getAll() { return dao.getAllBeans(); }
}
