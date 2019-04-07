package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.model.User;

public interface UserDao {

	User getUserById(int id);

	User getUserByEmail(String email);

	User getUserByEmailPassword(String email, String password);

	int addUser(String email, String password, String firstName, String lastName);

	void updateUser(User user);

	void resetUserPassword(User user);

	void updateUserLocation(User user);

	User findUserByResetToken(String resetToken);

	public Collection<User> findUsers(String firstName, String lastName, String city);


}
