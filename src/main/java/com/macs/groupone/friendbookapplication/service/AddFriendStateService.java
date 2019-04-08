package com.macs.groupone.friendbookapplication.service;

import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.model.User;

@Service
public class AddFriendStateService implements IStateService {
	
	private boolean FRIEND_TOKEN_VALUE = true;

	FriendsService friendsService = new FriendsService();

	@Override
	public void handleState(User friend, User user) {
		
		friendsService.updateFriendToken(friend);
		friendsService.addFriend(friend, user);
		friend.setFriendToken(FRIEND_TOKEN_VALUE);
	}

}
