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
import com.macs.groupone.friendbookapplication.dao.IFriendsDao;
import com.macs.groupone.friendbookapplication.jdbc.RowMapper;
import com.macs.groupone.friendbookapplication.model.Friend;
import com.macs.groupone.friendbookapplication.model.User;


public class FriendsDaoImpl extends AbstractDao implements IFriendsDao {
	
	public final  Logger logger = Logger.getLogger(FriendsDaoImpl.class);
	
	private static final String ADD_FRIEND = "{call addFriend(?, ?)}";
	private static final String REMOVE_FRIEND = "{call removeFriend(?)}";
	private static final String UPDATE_FRIEND_TOKEN ="{call updateFriendToken(?)}";
	private static final String FIND_FRIEND ="{call findFriend(?)}";
	private static final String CONFIRM_FRIEND="{call confirmFriend(?)}";
	private static final String UPDATE_CONFIRM_TOKEN="{call updateConfirmToken(?)}";
	private static final String REMOVE_FRIEND_USER="{call removeFriendUser(?)}";
	private static final String CLEAR_FRIEND_CONFIRM_TOKEN="{call clearFriendConfirmToken(?)}";
	private static final String CLEAR_FRIEND_TOKEN="{call clearFriendToken(?)}";

	private static final RowMapper<Friend> FRIENDS_MAPPER = new RowMapper<Friend>() {
		@Override
		public Friend map(final ResultSet resultSet) throws SQLException {
			final Friend friend = new Friend();
			friend.setUserid(resultSet.getInt("userid"));
			friend.setFriendid(resultSet.getInt("friendid"));
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
	public void confirmFriend(User user) {
		jdbcManager().update(CONFIRM_FRIEND, user.getId());
		
	}

	public void updateConfirmToken(User friend) {
		jdbcManager().update(UPDATE_CONFIRM_TOKEN, friend.getId());
		
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
	
	public Collection<User> findFriends(User user) {
		Collection<Friend> results = new ArrayList<>(); 
		results=jdbcManager().select(FIND_FRIEND, FRIENDS_MAPPER, user.getId()); 
		Set<Integer> friendSet= new HashSet<>(); 
		friendSet.add(user.getId());
		logger.debug("Adding current user as frined in list : "+user.getEmail() + "User Id : "+user.getId());
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
