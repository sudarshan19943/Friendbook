package com.macs.groupone.friendbookapplication.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.MessageDaoImpl;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class MessageService implements IService{

	@Autowired
	MessageDaoImpl messageDaoImpl;
	 public MessageService() {
		 
	 }
	
	public void addNewPost(User sender, User recipient, String post) {
		messageDaoImpl.addNewPost(sender, recipient, post);
	}

	
	public Collection<Message> getAll(User userOne, User userTwo) {
		return null;
	}

	
	public Collection<Message> getLast(User userOne) {
		return null;
	}

	public void getMessage(User user) {
		messageDaoImpl.getMessage(user);
	}

	
}
