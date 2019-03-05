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
	
	
	
	private static final Logger log = LoggerFactory.getLogger(FriendsDaoImpl.class);

	static final String SQL_GET_FRIEND_LIST = "SELECT id, email, first_name, last_name, city, country"
			.concat("CONCAT_WS(' ', first_name, last_name) AS name ")
			.concat("FROM users ")
			.concat("INNER JOIN friends AS userFriendsList ON users.id = userFriendsList.friendid AND userFriendsList.userid = ? ")
			.concat("LEFT JOIN friends AS FriendsOfUser ON users.id = FriendsOfUser.userid AND FriendsOfUser.friendid = ? ")
			.concat("ORDER BY name; ");
	
	
	public FriendsDaoImpl() {

	}

	private static final RowMapper<User> FRIENDS_MAPPER = new RowMapper<User>() {

		@Override
		public User map(final ResultSet resultSet) throws SQLException {
			final User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setEmail(resultSet.getString("email"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setCity(resultSet.getString("city"));
			user.setCountry(resultSet.getString("country"));
			return user;
		}
	};

	@Override
	public long addFriend(User user, User friend) {
		final long id = jdbcManager().insertAndGetId("INSERT INTO friends (userid, friendid) VALUES (?, ?)",user.getId(), friend.getId());
		return (int) id;
	}

	@Override
	public void removeFriend(User user, User friend) {
		jdbcManager().update("DELETE FROM friends WHERE userid = ? AND friendid = ?", user.getId(), friend.getId());
		
	}

	

	@Override
	public Collection<User> getFriendList(User user) {
		         log.info(SQL_GET_FRIEND_LIST);
			return (ArrayList<User>) jdbcManager().select(SQL_GET_FRIEND_LIST, FRIENDS_MAPPER, user.getId(),
					user.getId());
	
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}

	@Override
	public Collection<User> getFriendList(User user, int recordsPerPage, int i, String searchText) {
		return null;
	}

}
