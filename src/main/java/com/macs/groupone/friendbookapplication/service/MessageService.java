package com.macs.groupone.friendbookapplication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.dao.impl.MessageDaoImpl;
import com.macs.groupone.friendbookapplication.dao.impl.UserDaoImpl;
import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Friend;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;

@Service
public class MessageService implements IService {

	@Autowired
	MessageDaoImpl messageDaoImpl;

	@Autowired
	CommentService commentService;

	public MessageService() {

	}

	public void addNewPost(User sender, String post) {
		messageDaoImpl.addNewPost(sender, post);
	}

	public Collection<Post> getAll(User userOne, User userTwo) {
		return null;
	}

	public Collection<Post> getLast(User userOne) {
		return null;
	}



	public LinkedHashMap<String, Post> getMessagesByTimeStampWithComments(User currentUser,
			Collection<User> listOfFriends) {
		HashMap<String, User> friendsAndThierPostsWithTime = new HashMap<String, User>();
		ArrayList<User> friendsWithTheirPostsAndComments = new ArrayList<User>();
		for (Iterator iterator = listOfFriends.iterator(); iterator.hasNext();) {
			User friend = (User) iterator.next();
			ArrayList<Post> friendPosts = messageDaoImpl.getMessage(friend);
			// for each post set arrayListOFComments
			for (Iterator iterator2 = friendPosts.iterator(); iterator2.hasNext();) {
				Post post = (Post) iterator2.next();
				List<Comment> commentsPerPost = commentService.getComment((int) post.getId());
				post.setComments(commentsPerPost);
			}
			friend.setPosts(friendPosts);
			friendsWithTheirPostsAndComments.add(friend);
		}

		// 2. sort post by timestamp
		for (Iterator iterator = friendsWithTheirPostsAndComments.iterator(); iterator.hasNext();) {
			User friend = (User) iterator.next();
			ArrayList postsForThisUSer = friend.getPosts();
			for (int i = 0; i < postsForThisUSer.size(); i++) {
				Post post = (Post) postsForThisUSer.get(i);
				friendsAndThierPostsWithTime.put(post.getTimeStamp(), friend);
			}
		}

		// now you have Date AndTime and user of that post-sort this Hasmap by date and
		// Time
		List<String> sortedKeys = new ArrayList<String>(friendsAndThierPostsWithTime.size());
		sortedKeys.addAll(friendsAndThierPostsWithTime.keySet());
		Collections.sort(sortedKeys, Collections.reverseOrder());

		// now you have sorted Date
		// fetch corresponding user from map and save in linked hasmap in order of keys
		// date

		LinkedHashMap<String, User> userPostOrderByDate = new LinkedHashMap<String, User>();
		LinkedHashMap<String, Post> messageToreturn = new LinkedHashMap<String, Post>();
		for (Iterator iterator = sortedKeys.iterator(); iterator.hasNext();) {
			String timeStampSorted = (String) iterator.next();
			User orderedUser = friendsAndThierPostsWithTime.get(timeStampSorted);
			ArrayList<Post> postByTime = orderedUser.getPosts();
			for (int i = 0; i < postByTime.size(); i++) {
				Post post = postByTime.get(i);
				if (timeStampSorted.equalsIgnoreCase(post.getTimeStamp())) {
					User clonedUser = new User();
					ArrayList posts = new ArrayList();
					ArrayList<Comment> comments = (ArrayList<Comment>) post.getComments();
					for (int j = 0; j < comments.size(); j++) {
						Comment comment = comments.get(j);
						User commentator = new UserDaoImpl().getUserById(comment.getSender());
						String commentatorName ="<HTML>"+"<b>"+commentator.getFirstName() + " " + commentator.getLastName() + " : " +" </b>"+"</HTML>"
								+ comment.getBody();
						comment.setBody(commentatorName);
					}
					posts.add(post);
					clonedUser.setId(orderedUser.getId());
					clonedUser.setFirstName(orderedUser.getFirstName());
					clonedUser.setLastName(orderedUser.getLastName());
					clonedUser.setPosts(posts);
					// orderedPost.put(timeStampSorted, post);
					if (currentUser.getId() == clonedUser.getId()) {
						messageToreturn.put("<HTML>"+"<b>"+" You"+"</b>"+"  posted a message on " +"<b>"+timeStampSorted+"</b>"+"</HTML>", post);
					} else {
						messageToreturn.put("<HTML>"+"Message Posted by " + "<b>"+clonedUser.getFirstName() + " "
								+ clonedUser.getLastName()+"</b>" + " on " + "<b>"+timeStampSorted+"</b>"+"</HTML>", post);
					}

					userPostOrderByDate.put(timeStampSorted, clonedUser); // if user has multiple posts each post should
																			// eb sorted by timestamp
					break;
				}
			}

		}

		// make final list to return

		return messageToreturn;
	}

}
