package com.macs.groupone.friendbookapplication.service;


import com.macs.groupone.friendbookapplication.model.User;

public class ConfirmFriendStateService implements IStateService {
	
	private static boolean CONFIRM_FRIEND_TOKEN_VALUE = true;
	private static boolean FRIEND_TOKEN_VALUE = false;
	
	FriendsService friendsService = new FriendsService();

	@Override
	public void handleState(User friend, User user) {
		
		friendsService.updateConfirmToken(user);
		user.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		friendsService.clearFriendToken(user); 
		user.setFriendToken(FRIEND_TOKEN_VALUE);
		friendsService.updateConfirmToken(friend);
		friend.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		
		friendsService.addFriend(friend, user);
		user.setFriendToken(CONFIRM_FRIEND_TOKEN_VALUE);
		user.setFriendConfirmationToken(FRIEND_TOKEN_VALUE);
		
	}
	
}
