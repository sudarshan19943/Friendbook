package com.macs.groupone.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.macs.groupone.common.DBUtil;
import com.macs.groupone.interfaces.UserDao;
import com.macs.groupone.model.User;


@Service
public class UserService implements UserDao {

	UserService() {
	}

	@Override
	public int getNumberOfUsers() {
		
		return 0;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmailPassword(String email, String password) {
		// TODO Auto-generated method stub
		DBUtil db = new DBUtil();
		try {
			if(db.readDataBase())
			{
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<User> getUserList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Optional<User> addUser(String email, String password, String first_name, String last_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(User user, String password) {
		// TODO Auto-generated method stub

	}

	/*
	 * public User findByEmail(String email) {
	 * 
	 * DBUtil db=new DBUtil(); try { db.readDataBase(); } catch (Exception e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } return null; }
	 * 
	 * public User findByConfirmationToken(String confirmationToken) { return
	 * userRepository.findByConfirmationToken(confirmationToken); }
	 * 
	 * public void saveUser(User user) { userRepository.save(user); }
	 */
}