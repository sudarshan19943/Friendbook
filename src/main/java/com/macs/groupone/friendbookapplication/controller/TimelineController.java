package com.macs.groupone.friendbookapplication.controller;


import java.util.Collection;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Post;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.CommentService;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
class TimelineController {
	@Autowired
	FriendsService friendsService;

	@Autowired
	MessageService messageService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
    LinkedHashMap<String,Post> listOfPostsFromAllMyFriendsSorted;
	
	@GetMapping(value = "/timeline") 
	public String showTimelinePage(Model model, HttpServletRequest request) {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("user");
		if(currentUser==null)
			return "redirect:login";
		//get all the posts which are mine and shared by friends...
		// so when i post- add this post to all my friends 
		//when any of my friend post- add to all his friends
		//when i open my timeline i see all mine m=posts
		Collection<User> listOfFriends=(Collection) friendsService.findFriends(currentUser);
		//get all the posts from list of friends...
		LinkedHashMap<String,Post> listOfPostsFromAllMyFriendsSorted=messageService.getMessagesByTimeStampWithComments(currentUser,listOfFriends);
		model.addAttribute("types", listOfPostsFromAllMyFriendsSorted);
		return "timeline"; 
	}


	@PostMapping(value = "/comment") 
	public ModelAndView processPost(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redir, 
			@RequestParam("comment") String commentVal,@RequestParam("post") String post) { 
		User commentCreator=(User)request.getSession().getAttribute("user");
		int postId=1;
		Comment comment=new Comment();
		comment.setSender(commentCreator.getId());
		//set it to who has recieed comment that is postID , sender id from post table
		comment.setRecipient(7);//whose post is
		comment.setBody("nice pic");
		commentService.addNewComment(comment,postId)
		;
		return modelAndView; 
	}
	
	// Going to reset page without a token redirects to login page
			@ExceptionHandler(MissingServletRequestParameterException.class)
			public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
				return new ModelAndView("redirect:login");
			}
			
	

}
