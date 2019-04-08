package com.macs.groupone.friendbookapplication.service;

import com.macs.groupone.friendbookapplication.model.User;

public class MockUserFactory implements IMockUserFactory{

	public User makeMockUserObject() {
		return new User();
	}

}
