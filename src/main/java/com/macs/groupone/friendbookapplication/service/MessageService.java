package com.macs.groupone.friendbookapplication.service;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;

public class MessageService implements MessageDao{

	@Override
	public void addNew(User sender, User recipient, String text) {
		
	}

	@Override
	public void removeUser(User user) {
		
	}

	@Override
	public Collection<Message> getAll(User userOne, User userTwo) {
		return null;
	}

	@Override
	public Collection<Message> getLast(User userOne) {
		return null;
	}

	
}
