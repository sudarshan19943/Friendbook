package com.macs.groupone.friendbookapplication.service;

import java.util.ArrayList;
import java.util.Collection;

import com.macs.groupone.friendbookapplication.dao.impl.DAOFactory;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.controller.FriendsController;
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

	private static final Logger log = Logger.getLogger(FriendsController.class);

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

	public void confirmFriend(User user) {
		friendsDaoImpl.confirmFriend(user);
	}

	public void updateConfirmToken(User friend) {
		friendsDaoImpl.updateConfirmToken(friend);

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

	public void removeUserFromFriendsList(User user, ArrayList<User> friendList) {

		try {

			for (int friendListIndex = 0; friendListIndex < friendList.size(); friendListIndex++) {
				if (friendList.get(friendListIndex).getId() == user.getId()) {
					friendList.remove(friendListIndex);
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IndexOutOfBoundsException er) {
			log.error(er);
			er.printStackTrace();
		}
	}
	public void removeFriendsfromUserList(ArrayList<User> userList, ArrayList<User> friendList) {

		try {

			for (int userListIndex = 0; userListIndex < userList.size(); userListIndex++) {
				for (int friendListIndex = 0; friendListIndex < friendList.size(); friendListIndex++) {
					if (userList.get(userListIndex).getId() == friendList.get(friendListIndex).getId()) {
						userList.remove(userListIndex);
					}
				}
			}
		} catch (NullPointerException e) {
			log.error(e);
			e.printStackTrace();
		}catch (IndexOutOfBoundsException er) {
			log.error(er);
			er.printStackTrace();
		}

	}
}
