package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.IUserDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.User;


public class UserDaoImpl extends AbstractDao implements IUserDao {
	
	final  Logger logger = Logger.getLogger(UserDaoImpl.class);

	private static final String UPDATE_USER_AVATAR = "{call updateUserImage(?, ?)}";
	private static final String GET_USER_BY_USER_ID = "{call getUserById(?)}";
	private static final String GET_USER_BY_EMAIL = "{call getUserByEmail(?)}";
	private static final String GET_USER_BY_EMAIL_AND_PASSWORD = "{call getUserByEmailPassword(?, ?)}";
	private static final String GET_RESET_TOKEN="{call findUserByResetToken(?)}";
	private static final String ADD_USER ="{call addUser(?, ?, ?, ?)}";
	private static final String GET_FRIENDS_AS_USER="{call findFriends(?)}";
	private static final String UPDATE_USER_PASSWORD="{call updateUser(?, ?, ?)}";
	private static final String UPDATE_USER_LOCATION="{call updateUserLocation(?, ?, ?,?)}";
	private static final String RESET_USER_PASSWORD="{call resetUserPassword(?, ?, ?, ?)}";
	private static final String GET_USER_LIST="{call getUserList(?,?,?,?,?)}";
	

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
			user.setFriendToken(resultSet.getBoolean("friend_token"));
			user.setFriendConfirmationToken(resultSet.getBoolean("friend_confirm_token"));
			Blob imageBlob = resultSet.getBlob("user_image");
			if(imageBlob!=null)
			{
				byte[] imageBytes = imageBlob.getBytes(1,(int) imageBlob.length());
				user.setUserImage(Base64.encodeBase64String(imageBytes));
			}
			return user;
		}
	};
	
	
	public void uploadAvatarAndSaveBLOB(Blob userImage,String userEmail)
	{
		jdbcManager().update(UPDATE_USER_AVATAR,userImage, userEmail);
	}
	
	@Override
	public User getUserById(int id) {
		final List<User> result = jdbcManager().select(GET_USER_BY_USER_ID, USER_MAPPER, id);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public User getUserByEmail(String email) {
		final List<User> result = jdbcManager().select(GET_USER_BY_EMAIL, USER_MAPPER, email);
		return result.isEmpty() ? null : result.get(0);
	}

	
	@Override
	public User getUserByEmailPassword(String email, String password) {
		final List<User> result = jdbcManager().select(GET_USER_BY_EMAIL_AND_PASSWORD, USER_MAPPER, email, password);
		return result.isEmpty() ? null : result.get(0);
	}



	@Override
	public int addUser(String email, String password, String first_name, String last_name) {
		final long id = jdbcManager().insert(ADD_USER, email, password, first_name, last_name);
		return (int) id;
	}

	@Override
	public void updateUser(User user) {
		jdbcManager().update(UPDATE_USER_PASSWORD, user.getConfirmationToken(), user.getEmail(), user.getEnabled());

	}
	
	@Override
	public void updateUserLocation(User user) {
		jdbcManager().update(UPDATE_USER_LOCATION, user.getCountryId(), user.getStateId(), user.getCityId(),user.getEmail());
	}
	
	
	@Override
	public void resetUserPassword(User user) {
		jdbcManager().update(RESET_USER_PASSWORD,user.getPassword(), user.getConfirmationToken(),user.getEmail(),user.getEnabled());
	}
	
	
	@Override
	public User findUserByResetToken(String resetToken) {
		final List<User> result = jdbcManager().select(GET_RESET_TOKEN, USER_MAPPER,
				resetToken);
		return result.isEmpty() ? null : result.get(0);
	}

	public Collection<User> findUsers(User user) {
		Collection<User> results = new ArrayList<>(); 

		results.addAll(jdbcManager().select(GET_USER_LIST, USER_MAPPER, user.getFirstName(), user.getLastName(), user.getCityId(), user.getStateId(), user.getCountryId())); 
		return results;
	}
	
	public Collection<User> findFriends(User user) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select(GET_FRIENDS_AS_USER, USER_MAPPER, user.getId())); 
		return results;
	}

}