package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;
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

	public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	public static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	public static final String GET_USER_BY_EMAIL_PASSWORD = "SELECT * FROM users WHERE email = ? and password = ?";
	public static final String INSER_INTO_USER = "INSERT INTO users (email, password, first_name, last_name) VALUES (?, ?, ?, ?)";
	public static final String UPDATE_USER_BY_ID = "UPDATE users SET confirmation_token=?, email=?, enabled=?, first_name=?, last_name=?, password =?, province=?,country=? WHERE  id=?";
	public static final String GET_USER_BY_RESET_TOKEN="SELECT * FROM users WHERE confirmation_token = ?";
	
	
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
			user.setCityId(resultSet.getString("city"));
			user.setStateId(resultSet.getString("province"));
			user.setCountryId(resultSet.getString("country"));
			user.setFriendToken(resultSet.getInt("friend_token"));
			user.setFriendConfirmationToken(resultSet.getInt("friend_confirm_token"));
			Blob imageBlob = resultSet.getBlob("user_image");
			if(imageBlob!=null)
			{
				byte[] imageBytes = imageBlob.getBytes(1,(int) imageBlob.length());
				System.out.println("base 64 bit..."+Base64.encodeBase64String(imageBytes));
				user.setUserImage(Base64.encodeBase64String(imageBytes));
			}
			
			return user;
		}
	};
	
	
	public void uploadAvatarAndSaveBLOB(Blob userImage,String userEmail)
	{
		jdbcManager().update("{call updateUserImage(?, ?)}",userImage, userEmail);
	}

	@Override
	public User getUserById(int id) {
		final List<User> result = jdbcManager().select("{call getUserById(?)}", USER_MAPPER, id);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public User getUserByEmail(String email) {
		final List<User> result = jdbcManager().select("{call getUserByEmail(?)}", USER_MAPPER, email);
		return result.isEmpty() ? null : result.get(0);
	}

	
	@Override
	public User getUserByEmailPassword(String email, String password) {
		final List<User> result = jdbcManager().select("{call getUserByEmailPassword(?, ?)}", USER_MAPPER, email, password);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public Collection<User> getUserList() {

		return null;
	}

	@Override
	public int addUser(String email, String password, String first_name, String last_name) {
		final long id = jdbcManager().insertAndGetId("{call addUser(?, ?, ?, ?)}", email, password, first_name, last_name);
		return (int) id;
	}

	@Override
	public void updateUser(User user) {
		jdbcManager().update("{call updateUser(?, ?, ?)}", user.getConfirmationToken(), user.getEmail(), user.getEnabled());

	}
	
	@Override
	public void updateUserLocation(User user) {
		jdbcManager().update("{call updateUserLocation(?, ?, ?,?)}", user.getCountryId(), user.getStateId(), user.getCityId(),user.getEmail());
	}
	
	
	@Override
	public void resetUserPassword(User user) {
		jdbcManager().update("{call resetUserPassword(?, ?, ?, ?)}",user.getPassword(), user.getConfirmationToken(),user.getEmail(),user.getEnabled());

	}
	
	
	@Override
	public User findUserByResetToken(String resetToken) {
		final List<User> result = jdbcManager().select("{call findUserByResetToken(?)}", USER_MAPPER,
				resetToken);
		return result.isEmpty() ? null : result.get(0);
	}

	public Collection<User> findUsers(String firstName, String lastName, String city, String state, String country) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select("{call getUserList(?,?,?,?,?)}", USER_MAPPER, firstName, lastName, city, state, country)); 
		return results;
	}	

}