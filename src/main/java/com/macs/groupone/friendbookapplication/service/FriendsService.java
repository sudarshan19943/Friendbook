package com.macs.groupone.friendbookapplication.service;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.dao.impl.DAOFactory;
import com.macs.groupone.friendbookapplication.dao.impl.FriendsDaoImpl;
import com.macs.groupone.friendbookapplication.model.User;

public class FriendsService implements IService {

	FriendsDaoImpl friendsDaoImpl = (FriendsDaoImpl) DAOFactory.getInstance().getFriendDao();

	private static IService friendsService;

	public static IService getFriendServiceInstance() {
		if (friendsService == null) {
			return new FriendsService();
		} else {
			return friendsService;
		}
	}

	public void addFriend(User friend, User user) {
		friendsDaoImpl.addFriend(friend, user);
	}

	public void removeFriend(User user) {
		friendsDaoImpl.removeFriend(user);
	}

	public void updateFriendToken(User user) {
		friendsDaoImpl.updateFriendToken(user);

	}

	public Collection<User> findFriends(User user) {
		Collection<User> friends = (Collection<User>) friendsDaoImpl.findFriends(user);
		return friends;
	}

	public Collection<User> findFriendsSuman(User user) {
		Collection<User> friends = (Collection<User>) friendsDaoImpl.findFriendsSuman(user);
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
