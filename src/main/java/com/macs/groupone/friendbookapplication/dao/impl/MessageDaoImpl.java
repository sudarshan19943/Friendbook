package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			messagebean.setDate(resultSet.getTimestamp("post_time"));
			messagebean.setRecipient(resultSet.getInt("recipient_id"));
			messagebean.setSender(resultSet.getInt("sender_id"));
			messagebean.setBody(resultSet.getString("post"));

			return messagebean; } };


			@Override
			public void addNewPost(User sender, User recipient, String post) {
				jdbcManager().update("{call addNewPost(?, ?, ?)}", sender.getId(), recipient.getId(), post);

			}

			@Override
			public void removePost(Message message) {
				jdbcManager().update("{call removePost(?)}", message.getId());

			}

			@Override
			public Collection<Message> getMessage(User user) {
				Collection<Message> results = new ArrayList<>(); 
				results.addAll(jdbcManager().select("{call getMessage(0)}",MESSAGE_MAPPER));
				return results;
			}
}
