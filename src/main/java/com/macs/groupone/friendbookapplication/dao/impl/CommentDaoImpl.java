package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.CommentDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;


@Service
public class CommentDaoImpl extends AbstractDao implements CommentDao {
	
	
	
	private static final Logger log = LoggerFactory.getLogger(FriendsDaoImpl.class);

	static final String SQL_GET_COMMENT_LIST = "SELECT comment_id, comment, sender_id, receiver_id, timestamp"
			.concat("FROM post ")
			.concat("INNER JOIN comment ON post.post_id = comment.post_id_fk ")
			.concat("ORDER BY timestamp; ");
	
	
	public CommentDaoImpl() {

	}


		  private static final RowMapper<Comment> COMMENT_MAPPER = new
				  RowMapper<Comment>() {
				  
				  @Override public Comment map(final ResultSet resultSet) throws SQLException {
				  final Comment commentbean = new Comment();
				  commentbean.setId(resultSet.getInt("comment_id"));
				  commentbean.setDate(resultSet.getDate("timestamp"));
				  commentbean.setRecipient(resultSet.getInt("receiver_id"));
				  commentbean.setSender(resultSet.getInt("sender_id"));
				  commentbean.setBody(resultSet.getString("comment"));
				  
				  return commentbean; } };
				 
				
				@Override
				public void addNewComment(User sender, User recipient, String comments, Comment comment) {
					final String SQL_ADD_NEW = "INSERT INTO comment (sender_id, receiver_id, comment, timestamp, comment_id) VALUES (?, ?, ?, ?, ?);";
					jdbcManager().update(SQL_ADD_NEW, sender.getId(), recipient.getId(), comments, comment.getDate(), comment.getId());

				}

				@Override
				public Collection<Comment> getComment(Message message) {
					final String SQL_GET_NEW_COMMENT = "SELECT * from comment where post_id = ?";
					Collection<Comment> commentDb = jdbcManager().select(SQL_GET_NEW_COMMENT,COMMENT_MAPPER,  message.getId());
					return commentDb;
				}

}

