package com.macs.groupone.friendbookapplication.service;


import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.macs.groupone.friendbookapplication.dao.UserDao;
import com.macs.groupone.friendbookapplication.dao.impl.UserDaoImpl;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class UserService  implements IService{

	public static final String SECRET = "secret";

	@Autowired
	UserDao userDao;
	@Autowired
	UserDaoImpl userDaoimpl;

	public UserService() {

	}

	public int getNumberOfUsers() {

		return 0;
	}

	public Collection<User> getUserById(int id) {
		return userDao.getUserById(id);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public User getUserByEmailPassword(String email, String password) {
		return userDao.getUserByEmailPassword(email, getEncryptedPassword(password));

	}

	public int addUser(String email, String password, String firstName, String lastName) {
		return userDao.addUser(email, getEncryptedPassword(password), firstName, lastName);

	}

	public User findUserByResetToken(String resetToken) {
		return userDao.findUserByResetToken(resetToken);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void resetUserPassword(User user) {
		user.setPassword(getEncryptedPassword(user.getPassword()));
		userDao.resetUserPassword(user);
	}

	public Collection<User> findUsers(@Valid User user) {
		Collection<User> users=(Collection<User>) userDaoimpl.findUsers(user);
		return users;
  
	public void removeUser(User user) {
		userDao.removeUser(user);
	}
	
	private String getEncryptedPassword(String password) {
		return PasswordEncryptionService.encrypt(password, SECRET);

	}


}