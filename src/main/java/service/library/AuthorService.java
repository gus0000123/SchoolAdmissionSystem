package service.library;

import java.util.List;

import bean.library.Author;
import dao.library.AuthorDAO;
import template.Service;

public class AuthorService implements Service<Author>
{
	private AuthorDAO dao;
	
	@Override
	public void insert(Author o) { dao.insert(o); }

	@Override
	public void update(Author o) { dao.update(o); }

	@Override
	public void delete(Author o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Author getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Author> getAll() { return dao.getAll(); }
	
	@Override
	public Author getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static AuthorService instance;
	public AuthorService() { this.dao = AuthorDAO.getInstance(); }
	
	public static AuthorService getInstance()
	{
		if (instance == null) instance = new AuthorService();
		return instance;
	}
}
