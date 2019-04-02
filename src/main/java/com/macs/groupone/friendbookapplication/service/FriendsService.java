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
	
	public void addFriend(User friend, User user) {
		friendsDaoImpl.addFriend(friend, user);
	}

	
	public void removeFriend(User user) {
		friendsDaoImpl.removeFriend(user);
	}

	public void updateFriendToken(User user) {
		friendsDaoImpl.updateFriendToken(user);
		
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


	public void confirmFriend(User user) {
		friendsDaoImpl.confirmFriend(user);
	}


	public void updateConfirmToken(User friend) {
		friendsDaoImpl.updateConfirmToken(friend);
		
	}


	public void updateFriendTokenInFriends(User friend) {
		friendsDaoImpl.updateFriendTokenInFriends(friend);
		
	}

	public void removeFriendUser(User user) {
		friendsDaoImpl.removeFriendUser(user);
	}


	public void clearFriendConfirmToken(User user) {
		friendsDaoImpl.clearFriendConfirmToken(user);
		
	}
	
	public void clearFriendToken(User user) {
		friendsDaoImpl.clearFriendToken(user);
		
	}
	
}
