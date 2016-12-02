package service.messenger;

import java.util.List;

import bean.messenger.Personal;
import dao.messenger.PersonalDAO;
import template.Service;

public class PersonalService implements Service<Personal>
{
	private PersonalDAO dao;
	
	@Override
	public void insert(Personal o) { dao.insert(o); }

	@Override
	public void update(Personal o) { dao.update(o); }

	@Override
	public void delete(Personal o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Personal getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Personal> getAll() { return dao.getAll(); }
	
	@Override
	public Personal getLastInsert() { return dao.getLastInsert(); }
	
	public List<Personal> getAllFromReceiverId(int id) { return dao.getAllByReceiverId(id); }
	
	// Singleton
	private static PersonalService instance;
	public PersonalService() { this.dao = PersonalDAO.getInstance(); }
	
	public static PersonalService getInstance()
	{
		if (instance == null) instance = new PersonalService();
		return instance;
	}
}
