package com.macs.groupone.friendbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.model.User;

@Service
public class ConfirmFriendStateService implements IStateService {
	
	private int CONFIRM_FRIEND_TOKEN_VALUE = 1;
	private int FRIEND_TOKEN_VALUE = 0;
	
	@Autowired
	FriendsService friendsService;

	@Override
	public void handleState(User friend, User user) {
		
		friendsService.updateConfirmToken(user);
		user.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		friendsService.clearFriendToken(user); 
		user.setFriendToken(FRIEND_TOKEN_VALUE);
		friendsService.updateConfirmToken(friend);
		friend.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		
		friendsService.addFriend(friend, user);
		
	}

}
