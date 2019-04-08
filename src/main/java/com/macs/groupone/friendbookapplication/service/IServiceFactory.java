package com.macs.groupone.friendbookapplication.service;

public interface IServiceFactory {

	IService getUserService();
	IService getFriendService();
	IService getAvatarService();
	IService getMessageService();
	IService getEmailService();
	IService getCommentService();

}
