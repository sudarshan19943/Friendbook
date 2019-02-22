package com.macs.groupone.interfaces;

import java.util.Collection;

import com.macs.groupone.model.Message;
import com.macs.groupone.model.User;

public interface MessageDao {
	
	void addNew(User sender, User recipient, String text);
    void removeUser(User user);
    Collection<Message> getAll(User userOne, User userTwo);
    Collection<Message> getLast(User userOne);

}
