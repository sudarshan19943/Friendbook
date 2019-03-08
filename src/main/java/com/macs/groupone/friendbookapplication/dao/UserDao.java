package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;
import org.springframework.stereotype.Component;

import com.macs.groupone.friendbookapplication.model.User;

@Component
public interface UserDao{

	int getNumberOfUsers();
	User getUserById(int id);
	User getUserByEmail(String email);
	User getUserByEmailPassword(String email, String password);
	Collection<User> getUserList();
	int addUser(String email, String password, String first_name, String last_name); 																						
	void updateUser(User user);
	void removeUser(User user);
	void changePassword(User user, String password);
    User findUserByResetToken(String resetToken);
	
}
