package com.macs.groupone.friendbookapplication.model;

public class Friend {

	private int userid;
	private int friendid;
	private int friend_token;
	private int confirm_token;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public int getFriendToken() {
		return friend_token;
	}

	public void setFriendToken(int friend_token) {
		this.friend_token = friend_token;
	}

	public int getConfirmFriendToken() {
		return confirm_token;
	}

	public void setConfirmFriendToken(int confirm_token) {
		this.confirm_token = confirm_token;
	}

}
