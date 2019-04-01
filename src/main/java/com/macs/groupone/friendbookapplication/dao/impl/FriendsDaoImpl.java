package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Friend;
import com.macs.groupone.friendbookapplication.model.User;


@Service
public class FriendsDaoImpl extends AbstractDao implements FriendsDao {
	
	private int friendToken;
	
	private static final Logger log = LoggerFactory.getLogger(FriendsDaoImpl.class);
	
	
	public FriendsDaoImpl() {
		friendToken = 0;
	}

	private static final RowMapper<Friend> FRIENDS_MAPPER = new RowMapper<Friend>() {
		@Override
		public Friend map(final ResultSet resultSet) throws SQLException {
			final Friend friend = new Friend();
			friend.setUserid(resultSet.getInt("userid"));
			friend.setFriendid(resultSet.getInt("friendid"));
			return friend;
		}
	};
  
	private static final RowMapper<User> USER_MAPPER = new RowMapper<User>() {
	@Override
	public User map(final ResultSet resultSet) throws SQLException {
		final User friend = new User();
		friend.setId(resultSet.getInt("id"));
		friend.setFirstName(resultSet.getString("first_name"));
		friend.setLastName(resultSet.getString("last_name"));
		friend.setCityId(resultSet.getString("city"));
		friend.setCountryId(resultSet.getString("country"));
		friend.setStateId(resultSet.getString("province"));
		return friend;
	}
};

	@Override
	public long addFriend(User friend, User user) {
		final long id = jdbcManager().insertAndGetId("{call addFriend(?, ?)}", friend.getId(), user.getId());
		return (int) id;
	}

	@Override
	public void removeFriend(User user) {
		jdbcManager().update("{call removeFriend(?)}", user.getId());
		
	}
	
	@Override
	public void updateFriendToken(User user) {
		jdbcManager().update("{call updateFriendToken(?)}", user.getId());
		
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}
	
	public Collection<User> findFriends(User user) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select("{call findFriends(?)}", USER_MAPPER, user.getId())); 
		return results;
	}

	public Collection<User> findFriendsSuman(User user) {
		Collection<Friend> results = new ArrayList<>(); 
		results=jdbcManager().select("{call findFriendsSuman(?)}", FRIENDS_MAPPER, user.getId()); 
		Set<Integer> friendSet= new HashSet<Integer>(); 
		
		//get all users who are my friends
		friendSet.add(user.getId());//first add yourself-as you are always your friend
		for (Iterator iterator = results.iterator(); iterator.hasNext();) {
			Friend friend = (Friend) iterator.next();
			if(user.getId()==friend.getUserid())
			{
				friendSet.add(friend.getFriendid());
			}else if(friend.getFriendid()==user.getId())
			{
				friendSet.add(friend.getUserid());
			}
		}
		//now make the user Friend list
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		final ArrayList<User> friendListOfUser= new ArrayList<User>(); 
	    for (Iterator iterator = friendSet.iterator(); iterator.hasNext();) {
			int friendId = (Integer) iterator.next();
			//create User object from friend object
		    User userbyID=(User) userDaoImpl.getUserById(friendId);
		    friendListOfUser.add(userbyID);
		}
		return friendListOfUser;
	}

	@Override
	public void confirmFriend(User user) {
		jdbcManager().update("{call confirmFriend(?)}", user.getId());
		
	}

	public void updateConfirmToken(User friend) {
		jdbcManager().update("{call updateConfirmToken(?)}", friend.getId());
		
	}

	public void updateFriendTokenInFriends(User friend) {
		jdbcManager().update("{call updateFriendTokenInFriends(?)}", friend.getId());
		
	}
	
	public void removeFriendUser(User user) {
		jdbcManager().update("{call removeFriendUser(?)}", user.getId());
		
	}

	public void clearFriendConfirmToken(User user) {
		jdbcManager().update("{call clearFriendConfirmToken(?)}", user.getId());
		
	}
	
	public void clearFriendToken(User user) {
		jdbcManager().update("{call clearFriendToken(?)}", user.getId());
		
	}

}
