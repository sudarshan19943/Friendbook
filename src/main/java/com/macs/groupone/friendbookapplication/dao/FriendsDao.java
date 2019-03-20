package com.macs.groupone.friendbookapplication.dao;


import java.util.Collection;

import org.springframework.stereotype.Component;

import com.macs.groupone.friendbookapplication.model.User;

@Component
public interface FriendsDao {
	
	long addFriend(User user, User friend);
    void removeFriend(User user, User friend);
    long getNumberOfFriends(User user, String searchText);
    Collection<User> getFriendList(User user);

}
