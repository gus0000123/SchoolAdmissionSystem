package com.mcit.kritth.bo.template;

import java.util.List;

import com.mcit.kritth.model.messenger.Personal;

public interface PersonalBO extends BaseBO<Personal> {
	List<Personal> getAllFromReceiverId(int id);
	List<Personal> getAllFromSenderId(int id);
}
