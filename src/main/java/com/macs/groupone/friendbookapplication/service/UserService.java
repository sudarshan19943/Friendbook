package com.macs.groupone.friendbookapplication.service;

import java.sql.Blob;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.dao.impl.DAOFactory;
import com.macs.groupone.friendbookapplication.dao.impl.UserDaoImpl;
import com.macs.groupone.friendbookapplication.model.User;

public class UserService implements IService {

	public static final String SECRET = "secret";
	final static Logger logger = Logger.getLogger(UserService.class);

	private static IService userService;

	public static IService getUserServiceInstance() {
		if (userService == null) {
			return new UserService();
		} else {
			return userService;
		}
	}

	UserDaoImpl userDaoImpl = (UserDaoImpl) DAOFactory.getInstance().getUserDao();

	public User getUserById(int id) {
		return userDaoImpl.getUserById(id);
	}

	public User getUserByEmail(String email) {
		return userDaoImpl.getUserByEmail(email);
	}

	public User getUserByEmailPassword(String email, String password) {
		return userDaoImpl.getUserByEmailPassword(email, getEncryptedPassword(password));

	}

	public int addUser(String email, String password, String firstName, String lastName) {
		return userDaoImpl.addUser(email, getEncryptedPassword(password), firstName, lastName);

	}

	public User findUserByResetToken(String resetToken) {
		return userDaoImpl.findUserByResetToken(resetToken);
	}

	public void updateUser(User user) {
		userDaoImpl.updateUser(user);
	}
	
	public void updateUserImage(Blob userImageBlob, String emailID) {
		userDaoImpl.uploadAvatarAndSaveBLOB(userImageBlob,emailID);
	}

	public void updateUserLocation(User user) {
		userDaoImpl.updateUserLocation(user);
	}

	public void resetUserPassword(User user) {
		user.setPassword(getEncryptedPassword(user.getPassword()));
		userDaoImpl.resetUserPassword(user);
	}

	public Collection<User> findUsers(User user) {
		Collection<User> users=(Collection<User>) userDaoImpl.findUsers(user);
		return users;
	}
	
	public Collection<User> findFriendsFromDatabase(User user) {
		Collection<User> friends=(Collection<User>) userDaoImpl.findFriends(user);
		return friends;
	}
	
	private String getEncryptedPassword(String password) {
		return PasswordEncryptionService.encrypt(password, SECRET);

	}

}