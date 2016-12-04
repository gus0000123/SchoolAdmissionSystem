package com.mcit.kritth.bo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.data.DepartmentCodeDAOImpl;
import com.mcit.kritth.model.data.DepartmentCode;

@Service("departmentCodeService")
@Transactional
public class DepartmentCodeBOImpl implements BO<DepartmentCode>
{
	@Autowired
	private DepartmentCodeDAOImpl dao;
	
	@Override
	public void insert(DepartmentCode o) { dao.insertBean(o); }

	@Override
	public void update(DepartmentCode o) { dao.updateBean(o); }

	@Override
	public void delete(DepartmentCode o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public DepartmentCode getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<DepartmentCode> getAll() { return dao.getAllBeans(); }
}
