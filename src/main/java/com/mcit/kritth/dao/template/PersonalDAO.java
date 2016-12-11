package com.mcit.kritth.dao.template;

import java.io.Serializable;
import java.util.List;

import com.mcit.kritth.model.messenger.Personal;

public interface PersonalDAO extends DAO<Personal> {
	List<Personal> getAllByReceiverId(Serializable id);
	List<Personal> getAllBySenderId(Serializable id);
}
