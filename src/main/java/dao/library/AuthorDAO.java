package dao.library;

import java.util.List;

import bean.library.Author;
import template.DAO;
import util.HibernateUtil;

public class AuthorDAO implements DAO<Author>
{
	@Override
	public void insert(Author o) { HibernateUtil.insert(o); }

	@Override
	public void update(Author o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Author.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAll() { return (List<Author>) HibernateUtil.loadAll(Author.class); }

	@Override
	public Author getByPrimaryKey(int id) { return (Author) HibernateUtil.load(Author.class, id); }

	// Singleton
	private static AuthorDAO instance;
	
	private AuthorDAO() { }
	public static AuthorDAO getInstance()
	{
		if (instance == null) instance = new AuthorDAO();
		return instance;
	}
}
