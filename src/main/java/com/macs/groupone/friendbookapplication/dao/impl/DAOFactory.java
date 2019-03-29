package com.macs.groupone.friendbookapplication.dao.impl;

import com.macs.groupone.friendbookapplication.dao.CommentDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.dao.UserDao;

public class DAOFactory implements IDAOFactory{

	@Override
	public UserDao makeUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public FriendsDao makeFriendDao() {
		return new FriendsDaoImpl();
	}

	@Override
	public MessageDao makeMessageDao() {
		return new MessageDaoImpl();
	}

	@Override
	public CommentDao makeCommentsDao() {
		return new CommentDaoImpl();
	}

}
