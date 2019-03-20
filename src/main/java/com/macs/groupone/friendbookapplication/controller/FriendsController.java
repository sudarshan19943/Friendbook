package com.macs.groupone.friendbookapplication.controller;


import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.CountryAndStateService;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class FriendsController {

	private static final Logger log = Logger.getLogger(FriendsController.class);

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	@Autowired
	FriendsService friendsService;

	@Autowired
	CountryAndStateService countryAndStateService;

	@Autowired AvatarService avatarService;


	// show friend page
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ModelAndView showFriendPage(Model model, ModelAndView modelAndView, @Valid User user,
			RedirectAttributes redirect) {
		//get list of friends
		user.setId(1);
//		ArrayList<User>friendList=(ArrayList<User>) friendsService.getFriendList(user);
//		modelAndView.addObject("friendList",friendList);
//		modelAndView.setViewName("friends");
//		log.info("List of friends" + friendList);
		return modelAndView;
	}


	// add friend 
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public ModelAndView addFriends(Model model, ModelAndView modelAndView, @Valid User user1,@Valid User user2,
			RedirectAttributes redirect) {
		friendsService.addFriend(user1,user2);
		log.debug("Friend added");
		return modelAndView;
	}


	// delete friend 
	@RequestMapping(value = "/deleteFriend", method = RequestMethod.DELETE)
	public ModelAndView deleteFriend(Model model, ModelAndView modelAndView, @Valid User user1,@Valid User user2,
			RedirectAttributes redirect) {
		//get list of friends
		friendsService.removeFriend(user1,user2);
		log.debug("Friend removed");
		//all the posts related to this person must be deleted from timeline
		return modelAndView;
	}



}
