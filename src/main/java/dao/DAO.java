package dao;

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
	void insert(T o);
	void update(T o);
	void removeByPrimaryKey(int id);
	List<T> getAll();
	T getByPrimaryKey(int id);
}
