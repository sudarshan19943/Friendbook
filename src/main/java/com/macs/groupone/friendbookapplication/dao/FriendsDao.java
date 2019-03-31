package com.macs.groupone.friendbookapplication.dao;


import java.util.Collection;

import org.springframework.stereotype.Component;

import com.macs.groupone.friendbookapplication.model.User;

@Component
public interface FriendsDao {
	
	long addFriend(User friend, User user);
    void removeFriend(User user);
    long getNumberOfFriends(User user, String searchText);
	void confirmFriend(User user);
	void updateFriendToken(User user);

}
