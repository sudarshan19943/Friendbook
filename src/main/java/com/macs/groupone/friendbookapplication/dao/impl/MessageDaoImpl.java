package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class MessageDaoImpl extends AbstractDao implements MessageDao {

	private static final Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);

	public MessageDaoImpl() {

	}

	private static final RowMapper<Post> MESSAGE_MAPPER = new
			RowMapper<Post>() {

		@Override public Post map(final ResultSet resultSet) throws SQLException {
			final Post messagebean = new Post();
			messagebean.setId(resultSet.getInt("post_id"));
			messagebean.setTimeStamp(resultSet.getTimestamp("post_time"));
			//messagebean.setRecipient(resultSet.getInt("recipient_id"));
			messagebean.setSender(resultSet.getInt("sender_id"));
			messagebean.setBody(resultSet.getString("post"));

			return messagebean; } };


			@Override
			public void addNewPost(User sender, String post) {
				jdbcManager().update("{call addNewPost(?, ?)}", sender.getId(), post);

			}

			@Override
			public void removePost(Post message) {
				jdbcManager().update("{call removePost(?)}", message.getId());

			}
			
			@Override
			public ArrayList<Post> getMessage(User user) {
				ArrayList<Post> results = new ArrayList<>(); 
				results.addAll(jdbcManager().select("{call getMessageSuman(?)}",MESSAGE_MAPPER, user.getId()));
				return results;
			}
			
			public ArrayList<Post> getPostCreator(int postID)
			{
				ArrayList<Post> results = new ArrayList<>();
				results.addAll(jdbcManager().select("{call getPostCreator(?)}",MESSAGE_MAPPER, postID));
				return results;
			}
}
