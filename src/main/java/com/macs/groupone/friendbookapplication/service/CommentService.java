package com.macs.groupone.friendbookapplication.service;


import java.util.List;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.dao.impl.CommentDaoImpl;
import com.macs.groupone.friendbookapplication.dao.impl.DAOFactory;
import com.macs.groupone.friendbookapplication.model.Comment;

public class CommentService implements IService 
{
	
	final static Logger logger = Logger.getLogger(CommentService.class);

	CommentDaoImpl commentDaoImpl = (CommentDaoImpl) DAOFactory.getInstance().getCommentsDao();

	private static IService commentService;

	public static IService getCommentServiceInstance() 
	{
		if (commentService == null) 
		{
			return new CommentService();
		} 
		else 
		{
			return commentService;
		}
	}

	public void addNewComment(Comment comment,int postId) 
	{
		commentDaoImpl.addNewComment(comment.getSender(),comment.getRecipient(),comment.getBody(),postId);
	}
	
	public List<Comment> getComment(int postId) 
	{
		return commentDaoImpl.getComment(postId);
	}
	
}
