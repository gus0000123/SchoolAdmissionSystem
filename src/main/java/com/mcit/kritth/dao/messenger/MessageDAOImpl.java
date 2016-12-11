package com.mcit.kritth.dao.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.kritth.dao.template.MessageDAO;
import com.mcit.kritth.dao.template.HibernateSupport;
import com.mcit.kritth.model.messenger.Message;

@SuppressWarnings("unchecked")
@Repository("messageDAO")
public class MessageDAOImpl extends HibernateSupport implements MessageDAO
{
	@Override
	public void insertBean(Message o) { insert(o); }

	@Override
	public void updateBean(Message o) { update(o); }

	@Override
	public void removeBeanByPrimaryKey(Serializable id) { deleteById(Message.class, id); }

	@Override
	public List<Message> getAllBeans() { return (List<Message>) loadAll(Message.class); }

	@Override
	public Message getModelByPrimaryKey(Serializable id) { return (Message) load(Message.class, id); }
}
