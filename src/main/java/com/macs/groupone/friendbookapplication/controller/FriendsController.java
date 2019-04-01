package com.macs.groupone.friendbookapplication.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class FriendsController {

	private static final Logger log = Logger.getLogger(FriendsController.class);
	private int FRIEND_TOKEN_VALUE = 0;
	private int CONFIRM_FRIEND_TOKEN_VALUE = 0;
	private boolean enableConfirmButton = false;
	private boolean enableRemoveButton = false;
	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	@Autowired
	FriendsService friendsService;

	@Autowired AvatarService avatarService;


	// show friend page
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ModelAndView showFriendPage(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect) {

		return modelAndView;
	}
	
	//On clicking the find friends button, combined results of friends and users are shown
	@RequestMapping(value = "/friends", params = "findFriends", method = RequestMethod.POST)
	public ModelAndView findFriends(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName, @RequestParam("cityId") String city, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		try {
			
		ArrayList<User> userList=(ArrayList<User>) userService.findUsers(firstName, lastName, city);	
		ArrayList<User> friendList=(ArrayList<User>) friendsService.findFriends(user);
		
		model.addAttribute("enableConfirmButton", enableConfirmButton);
		
		//List<Integer> combineFriendWithUser = (List<Integer>) friendsService.findFriends(user);
		
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
		System.out.println("Inside find friends");
		System.out.println(user.getFriendToken());
		System.out.println(user.getFriendConfirmationToken());
		if(user.getFriendToken() == 1)
		{
			enableConfirmButton = true;
		}
		else if(user.getFriendConfirmationToken() == 1)
		{
			enableRemoveButton = true;
			user.setFriendConfirmationToken(0);
			user.setFriendToken(0);
		}
		
		System.out.println("---------------------------");
		modelAndView.addObject("friends", friendList);
		modelAndView.addObject("users", userList);
		modelAndView.setViewName("friends");
		log.info("List of friends" + friendList);
		}
		catch(NullPointerException e)
		{
			log.error(e);
			e.printStackTrace();
		}
		return modelAndView;
	
	}


	// delete friend 
	@RequestMapping(value = "/removefriends", params= "removeFriends", method = RequestMethod.POST)
	public String deleteFriend(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, HttpServletRequest request, @ModelAttribute("removefriendsForm") User user, @RequestParam("removeFriends") String post) {
		user.setId(Integer.parseInt(post));
		
		System.out.println("Inside remove friends");
		System.out.println(user.getFriendToken());
		System.out.println(user.getFriendConfirmationToken());
		friendsService.removeFriend(user);
		friendsService.removeFriendUser(user);
		user.setFriendConfirmationToken(0);
		user.setFriendToken(0);
		
		modelAndView.addObject("user", user);
		log.debug("Friend removed");
		//all the posts related to this person must be deleted from timeline
		return "redirect:friends";
	}
	
	//confirm friend request
	@RequestMapping(value = "/confirmfriend", params = "confirmfriend", method = RequestMethod.POST)
	public String confirmFriend(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, HttpServletRequest request, @RequestParam("confirmfriend") String confirmfriend, @ModelAttribute("confirmfriendForm") User friend)
	{
		friend.setId(Integer.parseInt(confirmfriend));
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		//friendsService.confirmFriend(user);
		friendsService.updateConfirmToken(user);
		friendsService.addFriend(friend, user);
		CONFIRM_FRIEND_TOKEN_VALUE = 1;
		friend.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		log.debug("Friend added");
		return "redirect:friends";
	}

	//add friend
	@RequestMapping(value = "/addfriends", params = "addFriends", method = RequestMethod.POST)
	public String addFriend(Model model, ModelAndView modelAndView, RedirectAttributes redirect, HttpServletRequest request, @RequestParam("addFriends") String addfriends, @ModelAttribute("addfriendsForm") User friend) 
	{
		friend.setId(Integer.parseInt(addfriends));
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		friendsService.updateFriendToken(friend);
		friendsService.updateFriendTokenInFriends(friend);
		friendsService.addFriend(friend, user);
		FRIEND_TOKEN_VALUE = 1;
		friend.setFriendToken(FRIEND_TOKEN_VALUE);
		log.debug("Friend added");
		//all the posts related to this person must be deleted from timeline
		return "redirect:friends";
	}


}
