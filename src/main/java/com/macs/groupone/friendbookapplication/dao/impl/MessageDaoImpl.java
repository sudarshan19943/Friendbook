package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macs.groupone.friendbookapplication.beans.Message;
import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.MessageDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.User;


public class MessageDaoImpl extends AbstractDao implements MessageDao {

	private static final Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);
	
	public MessageDaoImpl() {

	}
	
	public static   final String SQL_GET_LAST ="";
			
	private static final RowMapper<Message> MESSAGE_MAPPER = new RowMapper<Message>() {

		@Override
		public Message map(final ResultSet resultSet) throws SQLException {
			final Message messagebean = new Message();
			messagebean.setId(resultSet.getInt("id"));
			messagebean.setDate(resultSet.getDate("date"));
			messagebean.setRecipient(resultSet.getInt("recipient"));
			messagebean.setSender(resultSet.getInt("sender"));
			messagebean.setBody(resultSet.getString("body"));

			return messagebean;
		}
	};

	@Override
	public void addNew(User sender, User recipient, String text) {
		final String SQL_ADD_NEW = "INSERT INTO messages (sender, recipient, body) VALUES (?, ?, ?);";
		jdbcManager().update(SQL_ADD_NEW, sender.getId(), recipient.getId(), text);

	}

	@Override
	public void removeUser(User user) {
		final String SQL_REMOVE_USER_MESSAGES = "DELETE FROM messages WHERE (sender = ? OR recipient = ?);";
		jdbcManager().update(SQL_REMOVE_USER_MESSAGES, user.getId(), user.getId());
	}

	@Override
	public Collection<Message> getAll(User userOne, User userTwo) {
		final String SQL_GET_BY_USER = "SELECT id, date, sender, recipient, body FROM messages WHERE ((sender = ? and recipient = ?) or (sender = ? and recipient = ?)) ORDER BY date;";
		final long userOneId = userOne.getId();
		final long userTwoId = userTwo.getId();
		log.debug(SQL_GET_BY_USER);
		Collection<Message> messagesDb = jdbcManager().select(SQL_GET_BY_USER, MESSAGE_MAPPER, userOneId, userTwoId,
				userTwoId, userOneId);

		return messagesDb;
	}

	@Override
	public Collection<Message> getLast(User userOne) {
	        final long userId = userOne.getId();
	        log.debug(SQL_GET_LAST);
	        Collection<Message> messagesDb = jdbcManager().select(SQL_GET_LAST, MESSAGE_MAPPER, userId, userId);
		return messagesDb;
	}

}
