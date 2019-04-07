package com.macs.groupone.friendbookapplication.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

	MessageService messageService;

	@Before
	public void setUp() {
		messageService = new MessageService();
	}

	@Test
	public void testCloneUser() {
		User user = new User();
		user.setId(1);
		user.setFirstName("Yash");
		user.setLastName("Desai");
		User clonedUser = messageService.cloneUser(user);
		assertEquals(user.getId(), clonedUser.getId());
		assertEquals(user.getFirstName(), clonedUser.getFirstName());
		assertEquals(user.getLastName(), clonedUser.getLastName());

	}

	@Test
	public void testCustomizePostForSelf() {
		User currentUser = new User();
		currentUser.setId(1);
		currentUser.setFirstName("Yash");
		currentUser.setLastName("Desai");

		User cloneUser = new User();
		cloneUser.setId(1);
		cloneUser.setFirstName("Yash");
		cloneUser.setLastName("Desai");

		Post post = new Post();
		post.setBody("Testing Custom Post");

		LinkedHashMap<String, Post> messageToReturn = new LinkedHashMap<String, Post>();
		messageToReturn = messageService.customizePosts(currentUser, messageToReturn, cloneUser, new Date().toString(),
				post);

		if (currentUser.getId() == cloneUser.getId()) {
			for (String timeStamp : messageToReturn.keySet()) {
				assertTrue(timeStamp.contains("You</b>  posted"));
			}

		}

	}

	@Test
	public void testCustomizePostForFriend() {
		User currentUser = new User();
		currentUser.setId(1);
		currentUser.setFirstName("Yash");
		currentUser.setLastName("Desai");

		User cloneUser = new User();
		cloneUser.setId(2);
		cloneUser.setFirstName("Suman");
		cloneUser.setLastName("Singh");

		Post post = new Post();
		post.setBody("Testing Custom Post");

		LinkedHashMap<String, Post> messageToReturn = new LinkedHashMap<String, Post>();
		messageToReturn = messageService.customizePosts(currentUser, messageToReturn, cloneUser, new Date().toString(),
				post);
		if (currentUser.getId() != cloneUser.getId()) {
			for (String timeStamp : messageToReturn.keySet()) {
				assertTrue(timeStamp.contains("Message Posted by"));
			}

		}

	}
	
	
	@Test
	public void testCreateSeperatePostSortedByTimeStamp() {
		HashMap<String, User> friendsAndThierPostsWithTime =new HashMap<String, User>();
		
		ArrayList<Post> posts=new ArrayList<>();
		
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Yash");
		user.setLastName("Desai");
		
		Post post1=new Post();
		post1.setTimeStamp(new Timestamp(1));
		Post post2=new Post();
		post2.setTimeStamp(new Timestamp(2));
		Post post3=new Post();
		post3.setTimeStamp(new Timestamp(3));
		
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		
		user.setPosts(posts);
		

		User currentUser = new User();
		currentUser.setId(2);
		currentUser.setFirstName("Suman");
		currentUser.setLastName("Singh");

		Post post = new Post();
		post.setBody("Testing Custom Post");

		LinkedHashMap<String, Post> messageToReturn = new LinkedHashMap<String, Post>();
		

	}

	
	

}
