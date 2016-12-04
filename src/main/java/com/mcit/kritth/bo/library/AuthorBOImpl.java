package com.mcit.kritth.bo.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.BO;
import com.mcit.kritth.dao.library.AuthorDAOImpl;
import com.mcit.kritth.model.library.Author;

@Service("authorService")
@Transactional
public class AuthorBOImpl implements BO<Author>
{
	@Autowired
	private AuthorDAOImpl dao;
	
	@Override
	public void insert(Author o) { dao.insertBean(o); }

	@Override
	public void update(Author o) { dao.updateBean(o); }

	@Override
	public void delete(Author o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Author getById(int id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Author> getAll() { return dao.getAllBeans(); }
}
