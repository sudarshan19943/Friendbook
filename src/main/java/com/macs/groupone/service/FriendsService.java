package com.macs.groupone.service;

import java.util.Collection;

import com.macs.groupone.interfaces.FriendsDao;
import com.macs.groupone.model.User;

public class FriendsService implements FriendsDao{

	@Override
	public void addFriend(User user, User friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFriend(User user, User friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<User> getFriendList(User user, int recordsPerPage, int i, String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

}
