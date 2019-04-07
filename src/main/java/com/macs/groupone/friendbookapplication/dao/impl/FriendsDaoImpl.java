package com.macs.groupone.friendbookapplication.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.dao.AbstractDao;
import com.macs.groupone.friendbookapplication.dao.FriendsDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Friend;
import com.macs.groupone.friendbookapplication.model.User;


public class FriendsDaoImpl extends AbstractDao implements FriendsDao {
	
	final static Logger logger = Logger.getLogger(FriendsDaoImpl.class);
	
	public static final String ADD_FRIEND = "{call addFriend(?, ?)}";
	public static final String REMOVE_FRIEND = "{call removeFriend(?)}";
	public static final String UPDATE_FRIEND_TOKEN ="{call updateFriendToken(?)}";
	public static final String FIND_FRIENDS="{call findFriends(?)}";
	public static final String FIND_FRIEND_SUMAN ="{call findFriendsSuman(?)}";
	public static final String CONFIRM_FRIEND="{call confirmFriend(?)}";
	public static final String UPDATE_CONFIRM_TOKEN="{call updateConfirmToken(?)}";
	public static final String UPDATE_FRIEND_CONNECTION_TOKEN="{call updateFriendTokenInFriends(?)}";
	public static final String REMOVE_FRIEND_USER="{call removeFriendUser(?)}";
	public static final String CLEAR_FRIEND_CONFIRM_TOKEN="{call clearFriendConfirmToken(?)}";
	public static final String CLEAR_FRIEND_TOKEN="{call clearFriendToken(?)}";
	
	
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
		final long id = jdbcManager().insert(ADD_FRIEND, friend.getId(), user.getId());
		return (int) id;
	}

	@Override
	public void removeFriend(User user) {
		jdbcManager().update(REMOVE_FRIEND, user.getId());
		
	}
	
	@Override
	public void updateFriendToken(User user) {
		jdbcManager().update(UPDATE_FRIEND_TOKEN, user.getId());
		
	}

	@Override
	public long getNumberOfFriends(User user, String searchText) {
		return 0;
	}
	
	public Collection<User> findFriends(User user) {
		Collection<User> results = new ArrayList<>(); 
		results.addAll(jdbcManager().select(FIND_FRIENDS, USER_MAPPER, user.getId())); 
		return results;
	}

	

	@Override
	public void confirmFriend(User user) {
		jdbcManager().update(CONFIRM_FRIEND, user.getId());
		
	}

	public void updateConfirmToken(User friend) {
		jdbcManager().update(UPDATE_CONFIRM_TOKEN, friend.getId());
		
	}

	public void updateFriendTokenInFriends(User friend) {
		jdbcManager().update(UPDATE_FRIEND_CONNECTION_TOKEN, friend.getId());
		
	}
	
	public void removeFriendUser(User user) {
		jdbcManager().update(REMOVE_FRIEND_USER, user.getId());
		
	}

	public void clearFriendConfirmToken(User user) {
		jdbcManager().update(CLEAR_FRIEND_CONFIRM_TOKEN, user.getId());
		
	}
	
	public void clearFriendToken(User user) {
		jdbcManager().update(CLEAR_FRIEND_TOKEN, user.getId());
		
	}
	
	public Collection<User> findFriendsSuman(User user) {
		Collection<Friend> results = new ArrayList<>(); 
		results=jdbcManager().select(FIND_FRIEND_SUMAN, FRIENDS_MAPPER, user.getId()); 
		Set<Integer> friendSet= new HashSet<>(); 
		//get all users who are my friends
		friendSet.add(user.getId());//first add yourself-as you are always your friend
		for (Iterator<Friend> iterator = results.iterator(); iterator.hasNext();) {
			Friend friend = iterator.next();
			if(user.getId()==friend.getUserid())
			{
				friendSet.add(friend.getFriendid());
			}else if(friend.getFriendid()==user.getId())
			{
				friendSet.add(friend.getUserid());
			}
		}
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		final ArrayList<User> friendListOfUser= new ArrayList<>(); 
	    for (Iterator<Integer> iterator = friendSet.iterator(); iterator.hasNext();) {
			int friendId = (Integer) iterator.next();
		    User userbyID=userDaoImpl.getUserById(friendId);
		    friendListOfUser.add(userbyID);
		}
		return friendListOfUser;
	}

}
