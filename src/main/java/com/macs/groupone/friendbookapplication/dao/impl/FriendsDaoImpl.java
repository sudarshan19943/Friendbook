package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.User;


@Service
public class FriendsDaoImpl extends AbstractDao implements FriendsDao {
	
	private int friendToken;
	
	private static final Logger log = LoggerFactory.getLogger(FriendsDaoImpl.class);
	
	
	public FriendsDaoImpl() {
		friendToken = 0;
	}

	private static final RowMapper<User> FRIENDS_MAPPER = new RowMapper<User>() {

		@Override
		public User map(final ResultSet resultSet) throws SQLException {
			final User friend = new User();
			friend.setId(resultSet.getInt("id"));
			friend.setFirstName(resultSet.getString("first_name"));
			friend.setLastName(resultSet.getString("last_name"));
			friend.setCityId(resultSet.getString("city"));
			friend.setCountryId(resultSet.getString("country"));
			friend.setStateId(resultSet.getString("province"));
			return friend;
		}
	};

	@Override
	public long addFriend(User user) {
		final long id = jdbcManager().insertAndGetId("{call addFriend(?)}", user.getId());
		return (int) id;
	}

	@Override
	public void removeFriend(User user) {
		jdbcManager().update("{call removeFriend(?)}", user.getId());
		
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}

	public Collection<User> findFriends(User user) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select("{call findFriends(?)}", FRIENDS_MAPPER, user.getId())); 
		return results;
	}

	@Override
	public void confirmFriend(User user) {
		jdbcManager().update("{call confirmFriend(?)}", user.getId());
		
	}

}
