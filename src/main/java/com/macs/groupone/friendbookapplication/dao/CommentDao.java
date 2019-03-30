package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;
@Component
public interface CommentDao {

	Collection<Comment> getComment(Message message);
	public void addNewComment(Comment comments, Message post);

}