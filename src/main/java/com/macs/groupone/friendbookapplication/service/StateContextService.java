package com.macs.groupone.friendbookapplication.service;


import com.macs.groupone.friendbookapplication.model.User;

public class StateContextService {
	
	private IStateService state;

	public StateContextService(IStateService state){
		this.state = state;
	}
	
	public void executeState(User friend, User user)
	{
		state.handleState(friend, user);
	}

}
