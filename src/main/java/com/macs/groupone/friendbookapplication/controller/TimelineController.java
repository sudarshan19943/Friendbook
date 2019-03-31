package com.macs.groupone.friendbookapplication.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.Comment;
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
	
	@GetMapping(value = "/timeline") 
	public ModelAndView showTimelinePage(ModelAndView modelAndView, HttpServletRequest request) {
		HttpSession session=request.getSession();
		User userfromsession=(User) session.getAttribute("user");
		modelAndView.addObject("friends", friendsService.findFriends(userfromsession));
		modelAndView.addObject("message", messageService.getMessage(userfromsession));
		modelAndView.setViewName("timeline");
		System.out.println(" Profile Pic path..."+AvatarService.getProfileAvatar(userfromsession.getEmail()));
		modelAndView.addObject("avatarpic",AvatarService.getProfileAvatar(userfromsession.getEmail()));
		return modelAndView; 
	}


	@PostMapping(value = "/timeline", params="post") 
	public ModelAndView processPost(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redir, 
			@RequestParam("comment") String comment,@RequestParam("post") String post) { 
		//commentService.addNewComment(comment, post);
		return modelAndView; 
	}

}
