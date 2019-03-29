package com.macs.groupone.friendbookapplication.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.FriendsDaoImpl;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class FriendsService{

	
	@Autowired
	FriendsDaoImpl friendsDaoImpl;
	
	public void addFriend(User user, User friend) {
		
	}

	
	public void removeFriend(User user, User friend) {
		friendsDaoImpl.removeFriend(user, friend);
	}

	
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}

	
	public Collection<User> getFriendList(User user, int recordsPerPage, int i, String searchText) {
		ArrayList<User> friends=(ArrayList<User>) friendsDaoImpl.getFriendList( user);
		return friends;
	}
	
	public Collection<User> getFriendList(User user) {
		ArrayList<User> friends=(ArrayList<User>) friendsDaoImpl.getFriendList( user);
		return friends;
	}

}
