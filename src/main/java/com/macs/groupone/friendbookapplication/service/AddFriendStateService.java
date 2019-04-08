package com.macs.groupone.friendbookapplication.service;


import com.macs.groupone.friendbookapplication.model.User;

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
