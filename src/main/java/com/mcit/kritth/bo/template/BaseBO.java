package com.mcit.kritth.bo.template;

import java.io.Serializable;
import java.util.List;

public interface BaseBO<T>
{
	void insert(T o);
	void update(T o);
	void delete(T o);
	void deleteById(Serializable id);
	T getById(Serializable id);
	List<T> getAll();
}
