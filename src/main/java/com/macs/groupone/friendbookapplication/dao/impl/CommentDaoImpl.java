package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.ICommentDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Comment;

public class CommentDaoImpl extends AbstractDao implements ICommentDao 
{

	final  Logger logger = Logger.getLogger(CommentDaoImpl.class);
	public static final String ADD_NEW_COMMENT = "{call addNewComment(?, ?,?,?)}";
	public static final String GET_COMMENT = "{call getComment(?)}";


	private static final RowMapper<Comment> COMMENT_MAPPER = new RowMapper<Comment>() 
	{

		@Override
		public Comment map(final ResultSet resultSet) throws SQLException 
		{
			final Comment comment = new Comment();
			comment.setCommentId(resultSet.getInt("comment_id"));
			comment.setRecipient(resultSet.getInt("receiver_id"));
			comment.setSender(resultSet.getInt("sender_id"));
			comment.setBody(resultSet.getString("comment"));
			comment.setTimestamp(resultSet.getTimestamp("comment_time"));
			return comment;
		}
	};

	@Override
	public void addNewComment(int commentSenderId, int commentRecieverId, String commentBody, int postId) 
	{
		jdbcManager().insert(ADD_NEW_COMMENT, commentSenderId, commentRecieverId, commentBody, postId);
	}

	@Override
	public List<Comment> getComment(int postId) 
	{
		List<Comment> commentList = jdbcManager().select(GET_COMMENT, COMMENT_MAPPER, postId);
		return commentList;
	}

}

