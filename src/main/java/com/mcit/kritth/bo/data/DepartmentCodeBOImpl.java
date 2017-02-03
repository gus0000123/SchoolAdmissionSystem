package com.mcit.kritth.bo.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.DepartmentCodeBO;
import com.mcit.kritth.dao.template.DepartmentCodeDAO;
import com.mcit.kritth.model.data.DepartmentCode;

@Service("departmentCodeService")
@Transactional
public class DepartmentCodeBOImpl implements DepartmentCodeBO
{
	@Autowired
	private DepartmentCodeDAO dao;
	
	@Override
	public void insert(DepartmentCode o) { dao.insertBean(o); }

	@Override
	public void update(DepartmentCode o) { dao.updateBean(o); }

	@Override
	public void delete(DepartmentCode o) { dao.removeBeanByPrimaryKey(o.getDept_code()); }

	@Override
	@Transactional(noRollbackFor = ObjectNotFoundException.class)
	public DepartmentCode getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<DepartmentCode> getAll() { return dao.getAllBeans(); }
}
