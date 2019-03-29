package com.macs.groupone.friendbookapplication.controller;


import java.util.ArrayList;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		return modelAndView;
	}
	
	//On clicking the find friends button, combined results of friends and users are shown
	@RequestMapping(value = "/friends", params = "findFriends", method = RequestMethod.POST)
	public ModelAndView findFriends(Model model, ModelAndView modelAndView,  @Valid User user,
			RedirectAttributes redirect) {

		ArrayList<User>friendList=(ArrayList<User>) friendsService.findFriends(user);
		modelAndView.addObject("friends", friendList);
		ArrayList<User>userList=(ArrayList<User>) userService.findUsers(user);
		
		//Removes all friends from the user list
		for ( int userListIndex =0; userListIndex< userList.size(); userListIndex++)
		{
			for(int friendListIndex =0; friendListIndex<friendList.size(); friendListIndex++)
			{
				if(userList.get(userListIndex).getId() == friendList.get(friendListIndex).getId())
				{
					userList.remove(userListIndex);
				}
			}
		}
		
		modelAndView.addObject("users", userList);
		modelAndView.setViewName("friends");
		log.info("List of friends" + friendList);
		return modelAndView;
	}


	// delete friend 
	@RequestMapping(value = "/removeFriends", method = RequestMethod.GET)
	public String deleteFriend(Model model, ModelAndView modelAndView,@ModelAttribute("user") User user,
			RedirectAttributes redirect) {
		//get list of friends
		friendsService.removeFriend(user);
		modelAndView.addObject("user", user);
		log.debug("Friend removed");
		//all the posts related to this person must be deleted from timeline
		return "redirect:friends";
	}
	
	//confirm friend request
	@RequestMapping(value = "/confirmFriend", method = RequestMethod.GET)
	public String confirmFriend(Model model, ModelAndView modelAndView,@Valid User user,
			RedirectAttributes redirect) {
		friendsService.confirmFriend(user);
		log.debug("Friend added");
		return "redirect:friends";
	}

	//add friend
	@RequestMapping(value = "/addFriends", method = RequestMethod.GET)
	public String addFriend(Model model, ModelAndView modelAndView,@Valid User user,
			RedirectAttributes redirect) {
		friendsService.addFriend(user);
		log.debug("Friend added");
		//all the posts related to this person must be deleted from timeline
		return "redirect:friends";
	}


}
