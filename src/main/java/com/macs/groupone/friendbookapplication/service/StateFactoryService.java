package com.macs.groupone.friendbookapplication.service;

public class StateFactoryService {
	
	private static StateFactoryService stateFactory;
	
	public IStateService confirmFriendState() {
		return new ConfirmFriendStateService();
	}

	public IStateService addFriendState() {
		return new AddFriendStateService();
	}
	
	public IStateService removeFriendState() {
		return new RemoveFriendStateService();
	}
	
	public static StateFactoryService getInstance()
	{
		if(stateFactory == null)
		{
			stateFactory = new StateFactoryService();
		}
		return stateFactory;
	}
	
}
