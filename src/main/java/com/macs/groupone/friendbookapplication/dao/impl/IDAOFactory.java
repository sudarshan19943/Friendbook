package com.macs.groupone.friendbookapplication.dao.impl;


import com.macs.groupone.friendbookapplication.dao.IFriendsDao;
import com.macs.groupone.friendbookapplication.dao.ICommentDao;
import com.macs.groupone.friendbookapplication.dao.IMessageDao;
import com.macs.groupone.friendbookapplication.dao.IUserDao;

public interface IDAOFactory {
	IUserDao getUserDao();

	IFriendsDao getFriendDao();

	IMessageDao getMessageDao();

	ICommentDao getCommentsDao();
}
