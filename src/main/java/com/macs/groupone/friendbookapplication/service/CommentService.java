package com.macs.groupone.friendbookapplication.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.CommentDaoImpl;
import com.macs.groupone.friendbookapplication.model.Comment;

@Service
public class CommentService implements IService{

	
	@Autowired
	CommentDaoImpl commentDaoImpl;
	
	public CommentService()
	{
		
	}

	public void addNewComment(Comment comment,int postId) {
		commentDaoImpl.addNewComment(comment.getSender(),comment.getRecipient(),comment.getBody(),postId);
	}
	
	public List<Comment> getComment(int postId) {
		return commentDaoImpl.getComment(postId);
	}
	
}
