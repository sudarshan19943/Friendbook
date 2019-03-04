package com.macs.groupone.friendbookapplication.service;


import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.common.Util;
import com.macs.groupone.friendbookapplication.dao.UserDao;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public UserService() {

	}

	public int getNumberOfUsers() {

		return 0;
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public User getUserByEmailPassword(String email, String password) {
		return userDao.getUserByEmailPassword(email,password);
	}

	public Collection<User> getUserList() {

		return null;
	}

	public int addUser(String email, String password, String first_name, String last_name) {
		String encrpytedPassword=Util.encrypPasswordtSHY2(password);
		int user=userDao.addUser(email, encrpytedPassword, first_name, last_name);
		return user;
	}
	
	public User findUserByResetToken(String resetToken)
	{
		return userDao.findUserByResetToken(resetToken);
		
	}

	public void updateUser(User user) {
		 userDao.updateUser(user);
	}

	public void removeUser(User user) {
		userDao.removeUser(user);
	}

	public void changePassword(User user, String password) {

	}

}