package com.macs.groupone.friendbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.model.User;

@Service
public class AddFriendStateService implements IStateService {
	
	private int FRIEND_TOKEN_VALUE = 1;
	
	@Autowired
	FriendsService friendsService;

	@Override
	public void handleState(User friend, User user) {
		
		friendsService.updateFriendToken(friend); //Set friend's friend token to 1 - Friend request sent
		friendsService.addFriend(friend, user);
		friend.setFriendToken(FRIEND_TOKEN_VALUE);
	}

}
