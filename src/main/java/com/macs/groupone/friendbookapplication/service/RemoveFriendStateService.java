package com.macs.groupone.friendbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.model.User;

@Service
public class RemoveFriendStateService implements IStateService {
	
	private int clearToken = 0;
	
	@Autowired
	FriendsService friendsService;

	@Override
	public void handleState(User friend, User user) {

		friend.setFriendConfirmationToken(clearToken);
		friend.setFriendToken(clearToken);
		user.setFriendConfirmationToken(clearToken);
		user.setFriendToken(clearToken);
		friendsService.clearFriendConfirmToken(friend);
		friendsService.clearFriendToken(user);
		friendsService.clearFriendConfirmToken(user);
		friendsService.clearFriendToken(friend);
		friendsService.removeFriend(friend);
		friendsService.removeFriendUser(friend);
	}

}
