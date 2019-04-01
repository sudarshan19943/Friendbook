package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.CommentDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;


@Service
public class CommentDaoImpl extends AbstractDao implements CommentDao {

	public CommentDaoImpl() {

	}

	private static final Logger log = LoggerFactory.getLogger(CommentDaoImpl.class);
	

	private static final RowMapper<Comment> COMMENT_MAPPER = new
			RowMapper<Comment>() {

		@Override public Comment map(final ResultSet resultSet) throws SQLException {
			final Comment commentbean = new Comment();
			commentbean.setCommentId(resultSet.getInt("comment_id"));
			commentbean.setBody(resultSet.getString("comment"));
			return commentbean; } };

			@Override
			public void addNewComment(int commentSenderId,int commentRecieverId,String commentBody, int postId) {
				jdbcManager().insertAndGetId("{call addNewComment(?, ?,?,?)}",commentSenderId, commentRecieverId,commentBody,postId);

			}

			@Override
			public List<Comment> getComment(int postId) {
				List<Comment> commentList = jdbcManager().select("{call getComment(?)}",COMMENT_MAPPER,postId);
				return commentList;
			}

}

