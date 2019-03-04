package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.User;

public class FriendsDaoImpl extends AbstractDao implements FriendsDao {

	public FriendsDaoImpl() {

	}

	private static final RowMapper<User> FRIENDS_MAPPER = new RowMapper<User>() {

		@Override
		public User map(final ResultSet resultSet) throws SQLException {
			final User user = new User();
			user.setId(resultSet.getInt("id"));
			return user;
		}
	};

	@Override
	public long addFriend(User user, User friend) {
		final long id = jdbcManager().insertAndGetId("INSERT INTO friends (userid, friendid) VALUES (?, ?)",
				user.getId(), friend.getId());
		return (int) id;
	}

	@Override
	public void removeFriend(User user, User friend) {
		jdbcManager().update("DELETE FROM friends WHERE userid = ? AND friendid = ?", user.getId(), friend.getId());
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		ArrayList<User> users = null;
		final String SQL_GET_NUMBER_OF_FRIENDS = "SELECT count(friendid) AS id FROM friends "
				.concat("WHERE friends.userid = ?;");
		final String SQL_GET_NUMBER_OF_FRIENDS_WITH_SEARCH_PARAMETER = "SELECT count(id) AS id FROM users ".concat(
				"INNER JOIN friends AS userfriends ON users.id = userfriends.friendid AND userfriends.userid = ? ")
				.concat("WHERE LOWER(CONCAT_WS(' ', first_name, last_name)) LIKE LOWER(?) ");
		if (searchText == null || searchText.isEmpty()) {
			users = (ArrayList<User>) jdbcManager().select(SQL_GET_NUMBER_OF_FRIENDS, FRIENDS_MAPPER, user.getId());
		}

		else
			users = (ArrayList<User>) jdbcManager().select(SQL_GET_NUMBER_OF_FRIENDS_WITH_SEARCH_PARAMETER,
					FRIENDS_MAPPER, user.getId(), "%" + searchText + "%");

		return users.size();
	}

	@Override
	public Collection<User> getFriendList(User user, int recordsPerPage, int i, String searchText) {
		final String SQL_GET_FRIEND_LIST = "SELECT id, email, first_name, last_name, birth_date, phone, reg_date, sex, "
				.concat("CONCAT_WS(' ', first_name, last_name) AS name, ")
				.concat("CASE WHEN userfriends.friendid ISNULL THEN FALSE ELSE TRUE END AS isuserfriend, ")
				.concat("CASE WHEN friendofuser.userid ISNULL THEN FALSE ELSE TRUE END AS isfriendofuser ")
				.concat("FROM users ")
				.concat("INNER JOIN friends AS userfriends ON users.id = userfriends.friendid AND userfriends.userid = ? ")
				.concat("LEFT JOIN friends AS friendofuser ON users.id = friendofuser.userid AND friendofuser.friendid = ? ")
				.concat("ORDER BY name ").concat("limit ? offset ?;");
		final String SQL_GET_FRIEND_LIST_WITH_SEARCH_PARAMETER = "SELECT id, email, first_name, last_name, birth_date, phone, reg_date, sex, "
				.concat("CONCAT_WS(' ', first_name, last_name) AS name, ")
				.concat("CASE WHEN userfriends.friendid ISNULL THEN FALSE ELSE TRUE END AS isuserfriend, ")
				.concat("CASE WHEN friendofuser.userid ISNULL THEN FALSE ELSE TRUE END AS isfriendofuser ")
				.concat("FROM users ")
				.concat("INNER JOIN friends AS userfriends ON users.id = userfriends.friendid AND userfriends.userid = ? ")
				.concat("LEFT JOIN friends AS friendofuser ON users.id = friendofuser.userid AND friendofuser.friendid = ? ")
				.concat("WHERE LOWER(CONCAT_WS(' ', first_name, last_name)) LIKE LOWER(?) ").concat("ORDER BY name ")
				.concat("limit ? offset ?;");

		if (searchText == null || searchText.isEmpty()) {
			return (ArrayList<User>) jdbcManager().select(SQL_GET_FRIEND_LIST, FRIENDS_MAPPER, user.getId(),
					user.getId(), recordsPerPage, i, "");
		} else {
			return (ArrayList<User>) jdbcManager().select(SQL_GET_FRIEND_LIST_WITH_SEARCH_PARAMETER, FRIENDS_MAPPER,
					user.getId(), user.getId(), "%" + searchText + "%", recordsPerPage, i);
		}

	}

}
