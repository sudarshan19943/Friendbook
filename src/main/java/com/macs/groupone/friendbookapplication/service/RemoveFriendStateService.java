package com.macs.groupone.friendbookapplication.service;


import com.macs.groupone.friendbookapplication.model.User;


public class RemoveFriendStateService implements IStateService {
	
	private boolean clearToken = false;
	
	FriendsService friendsService = new FriendsService();

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
