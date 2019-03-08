package com.macs.groupone.friendbookapplication.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.UserService;




@Controller
public class NewPostController {
	
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	MessageService messageService;
	
	  @RequestMapping(value = "/newpost", method = RequestMethod.GET) 
	  public ModelAndView showNewpostPage(ModelAndView modelAndView, User user) {
			modelAndView.setViewName(Constants.NEW_POST_VIEW);
			return modelAndView; 
	  }
	 
	  
	  @RequestMapping(value = "/newpost", params="post", method = RequestMethod.POST) 
	  public ModelAndView processPost(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redir, @RequestParam("postphoto") String post) { 
	  Collection<User> friends = friendsService.getFriendList(user); 
	  //Iterate over collection of users and add the post in their timeline
	  Iterator<User> itr = friends.iterator();
	  while(itr.hasNext()) {
		 messageService.addNewPost((user), itr.next(), post);
		}
	  return modelAndView; 
	  }
	 
	  @RequestMapping(value = "/newpost", params="postphoto", method = RequestMethod.POST) 
	  public ModelAndView processPostPhoto(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redir, @RequestParam("postphoto") String postphoto) { 
	  Collection<User> friends = friendsService.getFriendList(user); 
	  //Iterate over collection of users and add the post in their timeline
	  Iterator<User> itr = friends.iterator();
	  while(itr.hasNext()) {
		 messageService.addNewPost((user), itr.next(), postphoto);
		}
	  return modelAndView; 
	  }
	 
}
