package com.macs.groupone.friendbookapplication.service;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.macs.groupone.friendbookapplication.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsServiceTest {
	
	FriendsService friendsService;
	
	IMockUserFactory factory;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception 
	{
		factory = new MockUserFactory();
		friendsService = new FriendsService();
	}
	
	@Test
	public void testremoveUserFromFriendsListSize()
	{
		User user1 = factory.makeMockUserObject();
		User user2 = factory.makeMockUserObject();
		User user3 = factory.makeMockUserObject();
		User user4 = factory.makeMockUserObject();
		
		user1.setId(0);
		user2.setId(1);
		user3.setId(2);
		user4.setId(3);
		
		ArrayList<User> friendList = new ArrayList<>();
		
		friendList.add(user1);
		friendList.add(user2);
		friendList.add(user3);
		friendList.add(user4);
		
		friendsService.removeUserFromFriendsList(user2, friendList);
		assertEquals(3, friendList.size());
		
	}
	
	@Test
	public void testForIndexOutOfBoundsRemoveUserFromFriendsList()
	{
		User user1 = factory.makeMockUserObject();
		user1.setId(0);
		exception.expect(IndexOutOfBoundsException.class);
		ArrayList<User> friendList = new ArrayList<>();
		friendList.add(user1);
		friendsService.removeUserFromFriendsList(user1, friendList);
		friendList.get(2);
		
	}
	
	@Test
	public void testForArrayOutOfBoundsExceptionRemoveUserFromFriendsList()
	{
		User user1 = factory.makeMockUserObject();
		user1.setId(0);		
		exception.expect(ArrayIndexOutOfBoundsException.class);
		ArrayList<User> friendList = new ArrayList<>();
		friendsService.removeUserFromFriendsList(user1, friendList);
		friendList.get(-1);
	}
	
	@Test
	public void testForNullPointerExceptionRemoveUserFromFriendsList()
	{
		User user1 = factory.makeMockUserObject();
		exception.expect(NullPointerException.class);
		ArrayList<User> friendList = null;
		friendsService.removeUserFromFriendsList(user1, friendList);
		friendList.get(0);
	}
	
}
