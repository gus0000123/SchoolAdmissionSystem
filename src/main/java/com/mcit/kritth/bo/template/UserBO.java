package com.mcit.kritth.bo.template;

import java.io.Serializable;

import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.User;

public interface UserBO extends BaseBO<User> {
	User getByPersonId(Serializable id);
	String getNewUsername(Person p);
}
