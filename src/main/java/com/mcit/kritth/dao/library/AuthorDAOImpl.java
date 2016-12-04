package com.mcit.kritth.dao.library;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.DAO;
import com.mcit.kritth.dao.HibernateSupport;
import com.mcit.kritth.model.library.Author;

@SuppressWarnings("unchecked")
@Repository("authorDAO")
public class AuthorDAOImpl extends HibernateSupport implements DAO<Author>
{
	@Override
	public void insertBean(Author o) { insert(o); }

	@Override
	public void updateBean(Author o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Author.class, id); }

	@Override
	public List<Author> getAllBeans() { return (List<Author>) loadAll(Author.class); }

	@Override
	public Author getModelByPrimaryKey(Serializable id) { return (Author) load(Author.class, id); }
}
