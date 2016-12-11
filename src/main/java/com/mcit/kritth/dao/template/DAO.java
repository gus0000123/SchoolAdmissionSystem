package com.mcit.kritth.dao.template;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Kritth
 *
 * @param <T> Object Type
 * @param <U> ID Type
 */
public interface DAO<T>
{
	void insertBean(T o);
	void updateBean(T o);
	void removeBeanByPrimaryKey(Serializable id);
	List<T> getAllBeans();
	T getModelByPrimaryKey(Serializable id);
}
