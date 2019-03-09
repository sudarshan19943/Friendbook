package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;
@Component
public interface CommentDao {

	Collection<Comment> getComment(Message message);
	void addNewComment(User sender, User recipient, String comment, Comment comment2);

}