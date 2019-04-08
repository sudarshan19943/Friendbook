package com.macs.groupone.friendbookapplication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.macs.groupone.friendbookapplication.dao.impl.DAOFactory;
import com.macs.groupone.friendbookapplication.dao.impl.MessageDaoImpl;
import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;

public class MessageService implements IService 
{
	final static Logger logger = Logger.getLogger(MessageService.class);

	private MessageDaoImpl messageDaoImpl = (MessageDaoImpl) DAOFactory.getInstance().getMessageDao();
	private CommentService commentService = (CommentService) ServiceFactory.getInstance().getCommentService();
	private UserService userService = (UserService) ServiceFactory.getInstance().getUserService();

	private static IService messageService;

	public static IService getMessageServiceInstance() {
		if (messageService == null) {
			return new MessageService();
		} else {
			return messageService;
		}
	}

	public void addNewPost(User sender, String post) {
		messageDaoImpl.addNewPost(sender, post);
	}

	public List<User> getFriendsWithPostsAndComments(Collection<User> listOfFriends) {
		ArrayList<User> friendsWithTheirPostsAndComments = new ArrayList<>();
		for (Iterator<User> friendsiterator = listOfFriends.iterator(); friendsiterator.hasNext();) {
			User friend = friendsiterator.next();
			ArrayList<Post> friendPosts = messageDaoImpl.getPosts(friend);
			for (Iterator<Post> friendPostsIterator = friendPosts.iterator(); friendPostsIterator.hasNext();) {
				Post post = friendPostsIterator.next();
				List<Comment> commentsPerPost = commentService.getComment((int) post.getId());
				post.setComments(commentsPerPost);
			}
			friend.setPosts(friendPosts);
			friendsWithTheirPostsAndComments.add(friend);
		}
		return friendsWithTheirPostsAndComments;
	}

	public Map<String, User> createTimestampPostMap(List<User> friendsWithTheirPostsAndComments) {
		HashMap<String, User> friendsAndThierPostsWithTime = new HashMap<>();
		for (Iterator<User> iterator = friendsWithTheirPostsAndComments.iterator(); iterator.hasNext();) {
			User friend = iterator.next();
			ArrayList<?> postsForThisUSer = friend.getPosts();
			for (int i = 0; i < postsForThisUSer.size(); i++) {
				Post post = (Post) postsForThisUSer.get(i);
				friendsAndThierPostsWithTime.put(post.getTimeStamp(), friend);
			}
		}
		return friendsAndThierPostsWithTime;
	}

	public List<String> SortTimeStampPostMap(Map<String, User> friendsAndThierPostsWithTime) {
		List<String> timeStampSortedKeys = new ArrayList<>(friendsAndThierPostsWithTime.size());
		timeStampSortedKeys.addAll(friendsAndThierPostsWithTime.keySet());
		Collections.sort(timeStampSortedKeys, Collections.reverseOrder());
		return timeStampSortedKeys;
	}

	public Map<String, Post> createSeperatePostSortedByTimeStamp(Map<String, User> friendsAndThierPostsWithTime,List<String> sortedTimeStamp, User currentUser) {
		LinkedHashMap<String, Post> messageToReturn = new LinkedHashMap<>();
		for (Iterator<String> sortedTimeStampIterator = sortedTimeStamp.iterator(); sortedTimeStampIterator.hasNext();) {
			String timeStampSorted = sortedTimeStampIterator.next();
			User orderedUser = friendsAndThierPostsWithTime.get(timeStampSorted);
			ArrayList<Post> postByTime = orderedUser.getPosts();
			for (int i = 0; i < postByTime.size(); i++) {
				Post post = postByTime.get(i);
				if (timeStampSorted.equalsIgnoreCase(post.getTimeStamp())) {
					messageToReturn = (LinkedHashMap<String, Post>) attachCommentsWithPost(messageToReturn, timeStampSorted, post,orderedUser, currentUser);
				}
			}
		}
		return messageToReturn;
	}

	public Map<String, Post> attachCommentsWithPost(Map<String, Post> messageToreturn,String timeStampSorted, Post post, User orderedUser,
			User currentUser) {
		ArrayList<Post> posts = new ArrayList<>();
		ArrayList<Comment> comments = (ArrayList<Comment>) post.getComments();
		
		    if(comments!=null)
		    {
		    	for (int j = 0; j < comments.size(); j++) {
					Comment comment = comments.get(j);
					User commentator = userService.getUserById(comment.getSender());
					String commentatorName = "<HTML>" + "<b>" + commentator.getFirstName() + " " + commentator.getLastName()
							+ " : " + " </b>" + "</HTML>" + comment.getBody();
					comment.setBody(commentatorName);
				}
		    }
			posts.add(post);
			User clonedUser = cloneUser(orderedUser);
			clonedUser.setPosts(posts);
			customizePosts(currentUser, messageToreturn, clonedUser, timeStampSorted, post);
		
		return messageToreturn;

	}

	public Map<String, Post> customizePosts(User currentUser, Map<String, Post> messageToreturn, User clonedUser,
			String timeStampSorted, Post post) {
		if (currentUser.getId() == clonedUser.getId()) {
			messageToreturn.put("<HTML>" + "<b>" + " You" + "</b>" + "  posted a message on " + "<b>" + timeStampSorted
					+ "</b>" + "</HTML>", post);
		} else {
			messageToreturn.put("<HTML>" + "Message Posted by " + "<b>" + clonedUser.getFirstName() + " "
					+ clonedUser.getLastName() + "</b>" + " on " + "<b>" + timeStampSorted + "</b>" + "</HTML>", post);
		}
		return messageToreturn;
	}

	public User cloneUser(User orderedUser) {
		User clonedUser = new User();
		clonedUser.setId(orderedUser.getId());
		clonedUser.setFirstName(orderedUser.getFirstName());
		clonedUser.setLastName(orderedUser.getLastName());
		return clonedUser;
	}

	public Map<String, Post> GetPostsSortedByTimeStamp(User currentUser, Collection<User> listOfFriends) {
		HashMap<String, User> friendsAndThierPostsWithTime = new HashMap<>();
		ArrayList<User> friendsWithTheirPostsAndComments = (ArrayList<User>) getFriendsWithPostsAndComments(listOfFriends);
		friendsAndThierPostsWithTime = (HashMap<String, User>) createTimestampPostMap(friendsWithTheirPostsAndComments);
		List<String> sortedTimeStamp = SortTimeStampPostMap(friendsAndThierPostsWithTime);
		return createSeperatePostSortedByTimeStamp(friendsAndThierPostsWithTime, sortedTimeStamp, currentUser);
	}

	public List<Post> getPostCreator(int postID) {
		return messageDaoImpl.getPostCreator(postID);
	}

}
