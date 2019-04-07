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
import com.macs.groupone.friendbookapplication.dao.UserDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.User;


public class UserDaoImpl extends AbstractDao implements UserDao {
	
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);

	public static final String UPDATE_USER_AVATAR = "{call updateUserImage(?, ?)}";
	public static final String GET_USER_BY_USER_ID = "{call getUserById(?)}";
	public static final String GET_USER_BY_EMAIL = "{call getUserByEmail(?)}";
	public static final String GET_USER_BY_EMAIL_AND_PASSWORD = "{call getUserByEmailPassword(?, ?)}";
	public static final String GET_RESET_TOKEN="{call findUserByResetToken(?)}";
	public static final String ADD_USER ="{call addUser(?, ?, ?, ?)}";
	public static final String UPDATE_USER_PASSWORD="{call updateUser(?, ?, ?)}";
	public static final String UPDATE_USER_LOCATION="{call updateUserLocation(?, ?, ?,?)}";
	public static final String RESET_USER_PASSWORD="{call resetUserPassword(?, ?, ?, ?)}";
	public static final String GET_USER_LIST="{call getUserList(?,?,?)}";
	

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

	public Collection<User> findUsers(String firstName, String lastName, String city) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select(GET_USER_LIST, USER_MAPPER, firstName, lastName, city)); 
		return results;
	}	

}