package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class MessageDaoImpl extends AbstractDao implements MessageDao {

	private static final Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	public MessageDaoImpl() {

	}
	
	  private static final RowMapper<Message> MESSAGE_MAPPER = new
	  RowMapper<Message>() {
	  
	  @Override public Message map(final ResultSet resultSet) throws SQLException {
	  final Message messagebean = new Message();
	  messagebean.setId(resultSet.getInt("post_id"));
	  messagebean.setDate(resultSet.getDate("timestamp"));
	  messagebean.setRecipient(resultSet.getInt("recipient_id"));
	  messagebean.setSender(resultSet.getInt("sender_id"));
	  messagebean.setBody(resultSet.getString("post"));
	  
	  return messagebean; } };
	 
	
	@Override
	public void addNewPost(User sender, User recipient, String post, Message message) {
		final String SQL_ADD_NEW = "INSERT INTO post (sender_id, recipient_id, post, timestamp) VALUES (?, ?, ?, ?);";
		jdbcManager().update(SQL_ADD_NEW, sender.getId(), recipient.getId(), post, message.getDate());

	}

	@Override
	public void removePost(Message message) {
		final String SQL_REMOVE_POST= "DELETE from post WHERE post_id = ?";
		jdbcManager().update(SQL_REMOVE_POST, message.getId());

	}

	@Override
	public Collection<Message> getMessage(User user) {
		final String SQL_GET_NEW_MESSAGE = "SELECT * from post where sender_id = ?";
		Collection<Message> messageDb = jdbcManager().select(SQL_GET_NEW_MESSAGE,MESSAGE_MAPPER,  user.getId());
		return messageDb;
	}

	/*
	 * @Override public Collection<Message> getAll(User userOne, User userTwo) {
	 * final String SQL_GET_BY_USER =
	 * "SELECT id, date, sender, recipient, body FROM messages WHERE ((sender = ? and recipient = ?) or (sender = ? and recipient = ?)) ORDER BY date;"
	 * ; final long userOneId = userOne.getId(); final long userTwoId =
	 * userTwo.getId(); log.debug(SQL_GET_BY_USER); Collection<Message> messagesDb =
	 * jdbcManager().select(SQL_GET_BY_USER, MESSAGE_MAPPER, userOneId, userTwoId,
	 * userTwoId, userOneId);
	 * 
	 * return messagesDb; }
	 */

	/*
	 * @Override public Collection<Message> getLast(User userOne) { final long
	 * userId = userOne.getId(); log.debug(SQL_GET_LAST); Collection<Message>
	 * messagesDb = jdbcManager().select(SQL_GET_LAST, MESSAGE_MAPPER, userId,
	 * userId); return messagesDb; }
	 */

}
