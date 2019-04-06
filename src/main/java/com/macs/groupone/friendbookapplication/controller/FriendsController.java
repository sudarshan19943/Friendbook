package com.macs.groupone.friendbookapplication.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AddFriendStateService;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.ConfirmFriendStateService;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.IStateService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.RemoveFriendStateService;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.StringUtils;

@Controller
public class FriendsController {
	private IStateService currentUserState;
	private static final Logger log = Logger.getLogger(FriendsController.class);
	private boolean enableConfirmButton = false;
	private boolean enableRemoveButton = false;
	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	@Autowired
	FriendsService friendsService;

	@Autowired 
	AvatarService avatarService;
	
	@Autowired
	RemoveFriendStateService removeFriendStateService;
	
	@Autowired
	ConfirmFriendStateService confirmFriendStateService;
	
	@Autowired
	AddFriendStateService addFriendStateService;
	


	// show friend page
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ModelAndView showFriendPage(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		if(user.getUserImage()==null)
		{
			//model.addAttribute("avatarpic",AvatarService.getDefaultAvatarImage());
		}else
		{
			model.addAttribute("avatarpic",user.getUserImage());
		}	
		model.addAttribute("usersForm", new User());
		ArrayList<User> friendList=(ArrayList<User>) friendsService.findFriends(user);
		
		//remove if it is myself
		for(int friendListIndex =0; friendListIndex<friendList.size(); friendListIndex++)
		{
			if(friendList.get(friendListIndex).getId() == user.getId())
			{
				friendList.remove(friendListIndex);
			}
		}
		
		if(user.getFriendToken() == 1 && user.getFriendConfirmationToken() == 0)
		{
			enableConfirmButton = true;
			enableRemoveButton = false;
		}
		else if(user.getFriendConfirmationToken() == 1 && user.getFriendToken() == 0)
		{
			enableConfirmButton = false;
			enableRemoveButton = true;
		}
		modelAndView.addObject("friends", friendList);
		model.addAttribute("enableConfirmButton", enableConfirmButton);
		model.addAttribute("enableRemoveButton", enableRemoveButton);

		return modelAndView;
	}
	
	//On clicking the find friends button, combined results of friends and users are shown
	@RequestMapping(value = "/friends", params = "findFriends", method = RequestMethod.POST)
	public ModelAndView findFriends(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, @ModelAttribute("usersForm") User usersForm, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		if(user.getUserImage()==null)
		{
			//model.addAttribute("avatarpic",AvatarService.getDefaultAvatarImage());
		}else
		{
			model.addAttribute("avatarpic",user.getUserImage());
		}
		try {
		System.out.println(usersForm.getFirstName() + usersForm.getLastName() + usersForm.getCityId() + usersForm.getStateId() + usersForm.getCountryId());
		ArrayList<User> userList=(ArrayList<User>) userService.findUsers(usersForm.getFirstName(), usersForm.getLastName(), usersForm.getCityId(), usersForm.getStateId(), usersForm.getCountryId());	
		ArrayList<User> friendList=(ArrayList<User>) friendsService.findFriends(user);

		for(int friendListIndex =0; friendListIndex<friendList.size(); friendListIndex++)
		{
			if(friendList.get(friendListIndex).getId() == user.getId())
			{
				friendList.remove(friendListIndex);
			}
		}
		
		
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
		System.out.println(user.getId());
		System.out.println(user.getFriendToken());
		System.out.println(user.getFriendConfirmationToken());
		System.out.println("---------------------------");
		
		
		modelAndView.addObject("friends", friendList);
		modelAndView.addObject("users", userList);
		modelAndView.setViewName("friends");
		
		if(user.getFriendToken() == 1 && user.getFriendConfirmationToken() == 0)
		{
			enableConfirmButton = true;
			enableRemoveButton = false;
		}
		else if(user.getFriendConfirmationToken() == 1 && user.getFriendToken() == 0)
		{
			enableConfirmButton = false;
			enableRemoveButton = true;
		}
		model.addAttribute("enableConfirmButton", enableConfirmButton);
		model.addAttribute("enableRemoveButton", enableRemoveButton);
		

		System.out.println("Inside find friends");
		System.out.println(user.getId());
		System.out.println(user.getFriendToken());
		System.out.println(user.getFriendConfirmationToken());
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
			RedirectAttributes redirect, HttpServletRequest request, @ModelAttribute("removefriendsForm") User friend, @RequestParam("removeFriends") String post) 
	{
		
		friend.setId(Integer.parseInt(post));
		
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);
		
		removeFriendStateService.handleState(friend, user);
		
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
		
		confirmFriendStateService.handleState(friend, user);

		log.debug("Friend confirmed");
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
		
		addFriendStateService.handleState(friend, user);

		log.debug("Friend added");
		//all the posts related to this person must be deleted from timeline
		return "redirect:friends";
	}


}
