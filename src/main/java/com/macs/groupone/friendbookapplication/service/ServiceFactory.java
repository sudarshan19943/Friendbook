package com.macs.groupone.friendbookapplication.service;


public class ServiceFactory implements IServiceFactory{

	@Override
	public IService getUserService() {
		return new UserService();
	}

	@Override
	public IService getFriendService() {
		return new FriendsService();
	}

	@Override
	public IService getAvatarService() {
		return new AvatarService();
	}

	@Override
	public IService getMessageService() {
		return new MessageService();
	}

	@Override
	public IService getEmailService() {
		return new EmailService();
	}

}
