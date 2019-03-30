package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.Message;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.UserService;


@Controller
public class NewPostController {
	private static final Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	private Date timestamp;

	@RequestMapping(value = "/newpost", method = RequestMethod.GET) 
	public ModelAndView showNewpostPage(ModelAndView modelAndView, User user, Model model) {
		modelAndView.setViewName(Constants.NEW_POST_VIEW);
		return modelAndView; 
	}


	@RequestMapping(value = "/newpost", params="post", method = RequestMethod.POST) 
	public ModelAndView processPost(ModelAndView modelAndView, @Valid User user, RedirectAttributes redir, @RequestParam("post") String post, @Valid Message message) { 
		messageService.addNewPost(user, user, post);
		ArrayList<User>friendList=(ArrayList<User>) friendsService.findFriends(user);
		for (int friendListIndex = 0 ; friendListIndex < friendList.size(); friendListIndex++)
		{
			messageService.addNewPost(user, friendList.get(friendListIndex), post);
		}
		
		return modelAndView; 
	}
	
	//Search functionality
	@RequestMapping(value = "/friends", params = "findFriends", method = RequestMethod.GET)
	public ModelAndView findFriends(Model model, ModelAndView modelAndView,  @Valid User user,
			RedirectAttributes redirect) {
		ArrayList<User>friendList=(ArrayList<User>) friendsService.findFriends(user);
		//write a stored procedure to extract first name, last name based on a given id
		for (int friendListIndex = 0 ; friendListIndex < friendList.size(); friendListIndex++)
		{
			ArrayList<User>userFriendList=(ArrayList<User>) userService.getUserById(friendList.get(friendListIndex).getId());
			String firstName = userFriendList.get(friendListIndex).getFirstName();
			String lastName = userFriendList.get(friendListIndex).getLastName();
			modelAndView.addObject(firstName);
			modelAndView.addObject(lastName);
		}
		modelAndView.addObject("friends", friendList);
		modelAndView.setViewName("friends");
		log.info("List of friends" + friendList);
		return modelAndView;
	}


}
