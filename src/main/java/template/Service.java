package template;

import java.util.List;

public interface Service<T>
{
	void insert(T o);
	void update(T o);
	void delete(T o);
	void deleteById(int id);
	T getById(int id);
	List<T> getAll();
}
