package com.macs.groupone.friendbookapplication.service;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.model.User;

public class FriendsService implements FriendsDao{

	@Override
	public void addFriend(User user, User friend) {
		
	}

	@Override
	public void removeFriend(User user, User friend) {
		
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}

	@Override
	public Collection<User> getFriendList(User user, int recordsPerPage, int i, String searchText) {
		return null;
	}

}
