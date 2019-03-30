package com.macs.groupone.friendbookapplication.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.dao.impl.FriendsDaoImpl;
import com.macs.groupone.friendbookapplication.dao.impl.MessageDaoImpl;
import com.macs.groupone.friendbookapplication.model.Comment;
import com.macs.groupone.friendbookapplication.model.Message;
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
	
	@RequestMapping(value = "/timeline", method = RequestMethod.GET) 
	public ModelAndView showTimelinePage(ModelAndView modelAndView, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		
		modelAndView.setViewName(Constants.TIMELINE_VIEW);
		modelAndView.addObject("friends", friendsService.findFriends(user));
		modelAndView.addObject("message", messageService.getMessage(user));
		modelAndView.setViewName("timeline");
		return modelAndView; 
	}


	@RequestMapping(value = "/timeline", params="post", method = RequestMethod.POST) 
	public ModelAndView processPost(ModelAndView modelAndView, HttpServletRequest request, RedirectAttributes redir, @ModelAttribute ("commentForm") Comment comment, @ModelAttribute ("commentForm") Message post ) { 
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		
		commentService.addNewComment(comment, post);
		
		return modelAndView; 
	}

}
