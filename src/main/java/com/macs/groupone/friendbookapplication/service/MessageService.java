package com.macs.groupone.friendbookapplication.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.MessageDaoImpl;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class MessageService {

	@Autowired
	MessageDaoImpl messageDaoImpl;
	
	public void addNewPost(User sender, User recipient, String text) {
		messageDaoImpl.addNewPost(sender, recipient, text);
	}

	
	public Collection<Message> getAll(User userOne, User userTwo) {
		return null;
	}

	
	public Collection<Message> getLast(User userOne) {
		return null;
	}

	
}
