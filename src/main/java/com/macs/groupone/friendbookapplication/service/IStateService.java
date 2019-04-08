package com.macs.groupone.friendbookapplication.service;

import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.model.User;

@Service
public interface IStateService {
	
	public void handleState(User friend, User user);

}
