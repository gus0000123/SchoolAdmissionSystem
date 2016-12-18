package com.mcit.kritth.dao.template;

import java.io.Serializable;

import com.mcit.kritth.model.data.User;

public interface UserDAO extends DAO<User> {
	User getByPersonID(Serializable id);
}
