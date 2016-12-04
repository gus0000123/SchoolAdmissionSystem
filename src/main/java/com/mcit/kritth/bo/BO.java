package com.mcit.kritth.bo;

import java.util.List;

public interface BO<T>
{
	void insert(T o);
	void update(T o);
	void delete(T o);
	void deleteById(int id);
	T getById(int id);
	List<T> getAll();
}
