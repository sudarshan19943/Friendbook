package com.macs.groupone.service;

import java.util.Collection;

import com.macs.groupone.interfaces.MessageDao;
import com.macs.groupone.model.Message;
import com.macs.groupone.model.User;

public class MessageService implements MessageDao{

	@Override
	public void addNew(User sender, User recipient, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Message> getAll(User userOne, User userTwo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Message> getLast(User userOne) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
