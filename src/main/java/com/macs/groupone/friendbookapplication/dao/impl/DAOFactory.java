package com.macs.groupone.friendbookapplication.dao.impl;

import com.macs.groupone.friendbookapplication.dao.CommentDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.dao.UserDao;

public class DAOFactory implements IDAOFactory{
	
	private static DAOFactory daoFactory;

	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public FriendsDao getFriendDao() {
		return new FriendsDaoImpl();
	}

	@Override
	public MessageDao getMessageDao() {
		return new MessageDaoImpl();
	}

	@Override
	public CommentDao getCommentsDao() {
		return new CommentDaoImpl();
	}

	
	public static DAOFactory getInstance()
	{
		if(daoFactory==null)
		{
			daoFactory=new DAOFactory();
			return  daoFactory;
		}
		return daoFactory;
	}
}
