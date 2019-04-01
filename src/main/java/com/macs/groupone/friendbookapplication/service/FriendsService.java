package com.macs.groupone.friendbookapplication.service;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.FriendsDaoImpl;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class FriendsService implements IService{

	
	@Autowired
	FriendsDaoImpl friendsDaoImpl;
	
	public void addFriend(User user) {
		friendsDaoImpl.addFriend(user);
	}

	
	public void removeFriend(User user) {
		friendsDaoImpl.removeFriend(user);
	}

	
	//previously written -fixing it
	/*public Collection<User> findFriends(User user) {
		Collection<User> friends=(Collection<User>) friendsDaoImpl.findFriends(user);
		return friends;
	}*/
	
	
	public Collection<User> findFriends(User user) {
		Collection<User> friends=(Collection<User>) friendsDaoImpl.findFriends(user);
		return friends;
	}


	public void confirmFriend(@Valid User user) {
		friendsDaoImpl.confirmFriend(user);
	}
	
}
