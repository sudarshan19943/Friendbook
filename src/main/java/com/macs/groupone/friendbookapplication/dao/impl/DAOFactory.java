package com.macs.groupone.friendbookapplication.dao.impl;


import com.macs.groupone.friendbookapplication.dao.ICommentDao;
import com.macs.groupone.friendbookapplication.dao.IFriendsDao;
import com.macs.groupone.friendbookapplication.dao.IMessageDao;
import com.macs.groupone.friendbookapplication.dao.IUserDao;

public class DAOFactory implements IDAOFactory{
	
	private static DAOFactory daoFactory;

	@Override
	public IUserDao getUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public IFriendsDao getFriendDao() {
		return new FriendsDaoImpl();
	}

	@Override
	public IMessageDao getMessageDao() {
		return new MessageDaoImpl();
	}

	@Override
	public ICommentDao getCommentsDao() {
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
