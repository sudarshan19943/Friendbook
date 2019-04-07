package com.macs.groupone.friendbookapplication.service;


public class ServiceFactory implements IServiceFactory{
	
	private static ServiceFactory serviceFactory;

	@Override
	public IService getUserService() {
		return UserService.getUserServiceInstance();
	}

	@Override
	public IService getFriendService() {
		return FriendsService.getFriendServiceInstance();
	}

	@Override
	public IService getAvatarService() {
		return  AvatarService.getAvatarServiceInstance();
	}

	@Override
	public IService getMessageService() {
		return MessageService.getMessageServiceInstance();
	}

	@Override
	public IService getEmailService() {
		return EmailService.getEmailServiceInstance();
	}
	
	@Override
	public IService getCommentService() {
		return CommentService.getCommentServiceInstance();
	}
	
	public static ServiceFactory getInstance()
	{
		if(serviceFactory==null)
		{
			serviceFactory=new ServiceFactory();
			return  serviceFactory;
		}
		return serviceFactory;
	}
	

}
