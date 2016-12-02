package dao.messenger;

import java.util.List;

import bean.messenger.Message;
import template.DAO;
import util.HibernateUtil;

public class MessageDAO implements DAO<Message>
{
	@Override
	public void insert(Message o) { HibernateUtil.insert(o); }

	@Override
	public void update(Message o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(Message.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAll() { return (List<Message>) HibernateUtil.loadAll(Message.class); }

	@Override
	public Message getByPrimaryKey(int id) { return (Message) HibernateUtil.load(Message.class, id); }

	@Override
	public Message getLastInsert()
	{
		List<Object> result = HibernateUtil.getNRowByColumn(Message.class, "message_id", 1, true);
		if (result != null && result.size() > 0)
			return (Message) result.get(0);
		else
			return null;
	}
	
	// Singleton
	private static MessageDAO instance;
	
	private MessageDAO() { }
	public static MessageDAO getInstance()
	{
		if (instance == null) instance = new MessageDAO();
		return instance;
	}
}
