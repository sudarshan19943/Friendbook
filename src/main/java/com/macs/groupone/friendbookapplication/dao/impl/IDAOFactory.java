package com.macs.groupone.friendbookapplication.dao.impl;

import com.macs.groupone.friendbookapplication.dao.CommentDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.dao.UserDao;

public interface IDAOFactory {
	UserDao makeUserDao();
	FriendsDao makeFriendDao();
	MessageDao makeMessageDao();
	CommentDao makeCommentsDao();
}
