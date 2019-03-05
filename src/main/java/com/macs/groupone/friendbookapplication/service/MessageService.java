package com.macs.groupone.friendbookapplication.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class MessageService {

	
	public void addNew(User sender, User recipient, String text) {
		
	}

	
	public void removeUser(User user) {
		
	}
	

	
	public Collection<Message> getAll(User userOne, User userTwo) {
		return null;
	}

	
	public Collection<Message> getLast(User userOne) {
		return null;
	}

	
}
