package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;

public interface MessageDao {
	
	void addNewPost(User sender, User recipient, String post, Message message);
    Collection<Message> getMessage(User user);
	void removePost(Message message);

}
