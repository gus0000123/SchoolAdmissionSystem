package com.mcit.kritth.bo.messenger;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.kritth.bo.template.MessageBO;
import com.mcit.kritth.dao.template.MessageDAO;
import com.mcit.kritth.model.messenger.Message;

@Service("messageService")
@Transactional
public class MessageBOImpl implements MessageBO
{
	@Autowired
	private MessageDAO dao;
	
	@Override
	public void insert(Message o) { dao.insertBean(o); }

	@Override
	public void update(Message o) { dao.updateBean(o); }

	@Override
	public void delete(Message o) { dao.removeBeanByPrimaryKey(o.getId()); }

	@Override
	public void deleteById(Serializable id) { dao.removeBeanByPrimaryKey(id); }

	@Override
	public Message getById(Serializable id) { return dao.getModelByPrimaryKey(id); }

	@Override
	public List<Message> getAll() { return dao.getAllBeans(); }
}
