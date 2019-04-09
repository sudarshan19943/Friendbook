package com.macs.groupone.friendbookapplication.dao;

import java.util.List;

import com.macs.groupone.friendbookapplication.model.Comment;

public interface ICommentDao {
	List<Comment> getComment(int postId);

	public void addNewComment(int commentSenderId, int commentRecieverId, String commentBody, int postId);
}