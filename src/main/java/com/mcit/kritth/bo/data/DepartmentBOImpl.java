package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.DepartmentDAOImpl;
import com.mcit.kritth.model.data.Department;

@Service("departmentService")
@Transactional
public class DepartmentBOImpl implements BO<Department>
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
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Department getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Department> getAll() { return dao.getAllBeans(); }
}
