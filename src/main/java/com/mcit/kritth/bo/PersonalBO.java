package com.mcit.kritth.bo;

import java.util.List;

import com.mcit.kritth.model.messenger.Personal;

public interface PersonalBO extends BO<Personal> {
	List<Personal> getAllFromReceiverId(int id);
	List<Personal> getAllFromSenderId(int id);
}
