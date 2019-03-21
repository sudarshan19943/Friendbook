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

	static final String SQL_GET_FRIEND_LIST = "SELECT id, email, first_name, lastname, city, country"
			.concat("CONCAT_aWS(' ', first_name, lastname) AS name ")
			.concat("FROM users ")
			.concat("INNER JOIN friends AS userFriendsList ON users.id = userFriendsList.friendid AND userFriendsList.userid = ? ")
			.concat("ORDER BY name; ");
	
	
	public FriendsDaoImpl() {

	}

	private static final RowMapper<User> FRIENDS_MAPPER = new RowMapper<User>() {

		@Override
		public User map(final ResultSet resultSet) throws SQLException {
			final User friend = new User();
			friend.setId(resultSet.getInt("friendid"));
			friend.setFirstName(resultSet.getString("first_name"));
			friend.setLastName(resultSet.getString("last_name"));
			return friend;
		}
	};

	@Override
	public long addFriend(User user, User friend) {
		final long id = jdbcManager().insertAndGetId("{call addFriend(?, ?)}",user.getId(), friend.getId());
		return (int) id;
	}

	@Override
	public void removeFriend(User user, User friend) {
		jdbcManager().update("{call removeFriend(?, ?)}", user.getId(), friend.getId());
		
	}

	

	@Override
	public Collection<User> getFriendList(User user) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select("{call getFriendList(1)}", FRIENDS_MAPPER)); 
		return results;
	
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}

	

}
