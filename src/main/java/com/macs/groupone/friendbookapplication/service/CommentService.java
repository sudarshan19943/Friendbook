package com.macs.groupone.friendbookapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.CommentDaoImpl;
import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Message;

@Service
public class CommentService implements IService{

	
	@Autowired
	CommentDaoImpl commentDaoImpl;
	

	public void addNewComment(Comment comments, Message post) {
		commentDaoImpl.addNewComment(comments, post);

	}
	
}
