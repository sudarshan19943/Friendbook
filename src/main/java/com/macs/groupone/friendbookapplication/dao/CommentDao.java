package com.macs.groupone.friendbookapplication.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.macs.groupone.friendbookapplication.model.Comment;


@Component
public interface CommentDao {
	List<Comment> getComment(int postId);
	public void addNewComment(int commentSenderId,int commentRecieverId,String commentBody, int postId);
	

}