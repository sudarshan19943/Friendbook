package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.UserDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class UserDaoImpl extends AbstractDao implements UserDao {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
	public static final String GET_USER_BY_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? and password = ?";
	public static final String INSER_INTO_USER = "INSERT INTO user (email, password, first_name, last_name) VALUES (?, ?, ?, ?)";
	public static final String UPDATE_USER_BY_ID = "UPDATE user SET confirmation_token=?, email=?, enabled=?, first_name=?, last_name=?, password =?, province=?,country=? WHERE  id=?";
	public static final String GET_USER_BY_RESET_TOKEN="SELECT * FROM user WHERE confirmation_token = ?";
	
	
	public UserDaoImpl() {

	}

	private static final RowMapper<User> USER_MAPPER = new RowMapper<User>() {

		@Override
		public User map(final ResultSet resultSet) throws SQLException {
			final User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setEmail(resultSet.getString("email"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setPassword(resultSet.getString("password"));
			user.setEnabled(resultSet.getBoolean("enabled"));
			return user;
		}
	};

	

	@Override
	public User getUserById(int id) {
		final List<User> result = jdbcManager().select(GET_USER_BY_ID, USER_MAPPER, id);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public User getUserByEmail(String email) {
		final List<User> result = jdbcManager().select(GET_USER_BY_EMAIL, USER_MAPPER, email);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public User getUserByEmailPassword(String email, String password) {
		final List<User> result = jdbcManager().select(GET_USER_BY_EMAIL_PASSWORD, USER_MAPPER, email, password);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public Collection<User> getUserList() {

		return null;
	}

	@Override
	public int addUser(String email, String password, String first_name, String last_name) {
		final long id = jdbcManager().insertAndGetId(INSER_INTO_USER, email, password, first_name, last_name);
		return (int) id;
	}

	@Override
	public void updateUser(User user) {
		jdbcManager().update(UPDATE_USER_BY_ID, user.getConfirmationToken(), user.getEmail(), user.getEnabled(),
				user.getFirstName(), user.getLastName(), user.getPassword(), user.getId(),user.getProvince(),user.getCountry());

	}
	
	@Override
	public User findUserByResetToken(String resetToken) {
		final List<User> result = jdbcManager().select(GET_USER_BY_RESET_TOKEN, USER_MAPPER,
				resetToken);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public void removeUser(User user) {

	}

	@Override
	public void changePassword(User user, String password) {

	}
	
	@Override
	public int getNumberOfUsers() {

		return 0;
	}

	

}