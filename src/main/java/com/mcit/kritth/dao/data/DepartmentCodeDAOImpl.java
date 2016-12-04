package com.mcit.kritth.dao.data;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.data.DepartmentCode;

@SuppressWarnings("unchecked")
@Repository("departmentCodeDAO")
public class DepartmentCodeDAOImpl extends HibernateSupport implements DAO<DepartmentCode>
{
	@Override
	public void insertBean(DepartmentCode o) { insert(o); }

	@Override
	public void updateBean(DepartmentCode o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(DepartmentCode.class, id); }

	@Override
	public List<DepartmentCode> getAllBeans() { return (List<DepartmentCode>) loadAll(DepartmentCode.class); }

	@Override
	public DepartmentCode getModelByPrimaryKey(Serializable id) { return (DepartmentCode) load(DepartmentCode.class, id); }
}
