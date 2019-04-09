package com.macs.groupone.friendbookapplication.dao;

import com.macs.groupone.friendbookapplication.model.User;

public interface IFriendsDao {

	long addFriend(User friend, User user);

	void removeFriend(User user);

	void confirmFriend(User user);

	void updateFriendToken(User user);

}
