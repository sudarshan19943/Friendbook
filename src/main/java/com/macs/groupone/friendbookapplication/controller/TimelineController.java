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
import com.macs.groupone.friendbookapplication.service.AvatarService;
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
		String currentUseremail=(String) session.getAttribute("email");
		if(currentUseremail==null)
			return "redirect:login";
		
		User findUserFromEmail=userService.getUserByEmail(currentUseremail) ; 
		Collection<User> listOfFriends=(Collection) friendsService.findFriends(findUserFromEmail);
		LinkedHashMap<String,Post> listOfPostsFromAllMyFriendsSorted=messageService.getMessagesByTimeStampWithComments(findUserFromEmail,listOfFriends);
		model.addAttribute("types", listOfPostsFromAllMyFriendsSorted);
		String emailfromsession=(String) request.getSession().getAttribute("email");
		User userFromSession=userService.getUserByEmail(emailfromsession);
		if(userFromSession.getUserImage()==null)
		{//show default image
			model.addAttribute("avatarpic",AvatarService.getDefaultAvatarImage());
		}else
		{
			System.out.println("image found in new post :"+userFromSession.getUserImage());
			model.addAttribute("avatarpic",userFromSession.getUserImage());
		}
		return "timeline"; 
	}


	@RequestMapping(value = "/comment", params = "comment", method = RequestMethod.POST)
	public String processPost(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redir, 
			@RequestParam("comment") String commentVal,@RequestParam("postId") String postId) { 
		
		String email=(String) request.getSession().getAttribute("email");
		User userByEmailAndPassword = userService.getUserByEmail(email);
		Comment comment=new Comment();
		comment.setSender(userByEmailAndPassword.getId());
		comment.setRecipient(7);//whose post is
		comment.setBody("Still in progrtess..");
		commentService.addNewComment(comment,9);
		return "redirect:timeline"; 
	}
	
	     // Going to reset page without a token redirects to login page
			@ExceptionHandler(MissingServletRequestParameterException.class)
			public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
				return new ModelAndView("redirect:login");
			}
			
	


}
