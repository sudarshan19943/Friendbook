package com.macs.groupone.friendbookapplication.interfaces;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.model.User;

public interface FriendsDao {
	
	void addFriend(User user, User friend);
    void removeFriend(User user, User friend);
    long getNumberOfFriends(User user, String searchText);
    Collection<User> getFriendList(User user, int recordsPerPage, int i, String searchText);

}