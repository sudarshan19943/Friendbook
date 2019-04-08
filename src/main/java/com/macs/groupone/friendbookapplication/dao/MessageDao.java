package com.macs.groupone.friendbookapplication.dao;

import java.util.Collection;

import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;

public interface MessageDao {
	
	void addNewPost(User sender, String post);
	public Collection<Post> getPosts(User user);
}
