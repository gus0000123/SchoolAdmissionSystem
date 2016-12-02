package service.messenger;

import java.util.List;

import bean.messenger.Message;
import dao.messenger.MessageDAO;
import template.Service;

public class MessageService implements Service<Message>
{
	private MessageDAO dao;
	
	@Override
	public void insert(Message o) { dao.insert(o); }

	@Override
	public void update(Message o) { dao.update(o); }

	@Override
	public void delete(Message o) { dao.removeByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(int id) { dao.removeByPrimaryKey(id); }

	@Override
	public Message getById(int id) { return dao.getByPrimaryKey(id); }

	@Override
	public List<Message> getAll() { return dao.getAll(); }
	
	@Override
	public Message getLastInsert() { return dao.getLastInsert(); }
	
	// Singleton
	private static MessageService instance;
	public MessageService() { this.dao = MessageDAO.getInstance(); }
	
	public static MessageService getInstance()
	{
		if (instance == null) instance = new MessageService();
		return instance;
	}
}
