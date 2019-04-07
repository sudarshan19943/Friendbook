package com.macs.groupone.friendbookapplication.dao;


import java.util.Collection;
import org.springframework.stereotype.Component;

import com.macs.groupone.friendbookapplication.model.User;

@Component
public interface UserDao {

	User getUserById(int id);
	User getUserByEmail(String email);
	User getUserByEmailPassword(String email, String password);
	int addUser(String email, String password, String first_name, String last_name);
	void updateUser(User user);
	void resetUserPassword(User user);
	void updateUserLocation(User user);
	User findUserByResetToken(String resetToken);
	public Collection<User> findUsers(User user);
	public Collection<User> findFriends(User user);

}
