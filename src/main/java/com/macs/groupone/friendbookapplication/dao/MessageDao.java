package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.beans.Message;
import com.macs.groupone.friendbookapplication.model.User;

public interface MessageDao {
	
	void addNewPost(User sender, User recipient, String text);
    void removeUser(User user);
    Collection<Message> getAll(User userOne, User userTwo);
    Collection<Message> getLast(User userOne);

}
