package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.IMessageDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;

public class MessageDaoImpl extends AbstractDao implements IMessageDao 
{
	final  Logger logger = Logger.getLogger(MessageDaoImpl.class);
	private static final String ADD_NEW_POST = "{call addNewPost(?, ?)}";
	private static final String GET_POSTS_BY_USER = "{call getMessageByUserID(?)}";
	private static final String GET_POST_CREATOR ="{call getPostCreator(?)}";
	

	private static final RowMapper<Post> MESSAGE_MAPPER = new RowMapper<Post>() 
	{
		@Override
		public Post map(final ResultSet resultSet) throws SQLException 
		{
			final Post message = new Post();
			message.setId(resultSet.getInt("post_id"));
			message.setTimeStamp(resultSet.getTimestamp("post_time"));
			message.setSender(resultSet.getInt("sender_id"));
			message.setBody(resultSet.getString("post"));
			return message;
		}
	};

	@Override
	public void addNewPost(User sender, String post) 
	{
		jdbcManager().update(ADD_NEW_POST, sender.getId(), post);
	}

	@Override
	public ArrayList<Post> getPosts(User user) 
	{
		ArrayList<Post> results = new ArrayList<>();
		results.addAll(jdbcManager().select(GET_POSTS_BY_USER, MESSAGE_MAPPER, user.getId()));
		return results;
	}
	
	public List<Post> getPostCreator(int postID)
	{
		ArrayList<Post> results = new ArrayList<>();
		results.addAll(jdbcManager().select(GET_POST_CREATOR,MESSAGE_MAPPER, postID));
		return results;
	}

}
