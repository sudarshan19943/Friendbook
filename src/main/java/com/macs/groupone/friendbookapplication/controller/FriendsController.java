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
			RedirectAttributes redirect,HttpServletRequest request) {
		String email=(String) request.getSession().getAttribute("email");
		
		if(email==null)
		{
			modelAndView.setViewName("redirect:login");
			return modelAndView;
		}
		
		User user=userService.getUserByEmail(email);
		//add Pic
				if(user.getUserImage()==null)
				{//show default image
					model.addAttribute("avatarpic",AvatarService.getDefaultAvatarImage());
				}else
				{
					System.out.println("image found in new post :"+user.getUserImage());
					model.addAttribute("avatarpic",user.getUserImage());
				}
				
			

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
		
		for(int friendListIndex =0; friendListIndex<friendList.size(); friendListIndex++)
		{
			if(friendList.get(friendListIndex).getId() == user.getId())
			{
				friendList.remove(friendListIndex);
			}
		}
		
		model.addAttribute("enableConfirmButton", enableConfirmButton);
		model.addAttribute("enableRemoveButton", enableRemoveButton);
		
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
		else if(user.getFriendConfirmationToken() == 1 && user.getFriendToken() == 1)
		{
			enableConfirmButton = false;
			enableRemoveButton = true;
		}
		
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
			RedirectAttributes redirect, HttpServletRequest request, @ModelAttribute("removefriendsForm") User friend, @RequestParam("removeFriends") String post) {
		friend.setId(Integer.parseInt(post));
		
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user=userService.getUserByEmail(emailfromsession);

		friend.setFriendConfirmationToken(0);
		friend.setFriendToken(0);
		user.setFriendConfirmationToken(0);
		user.setFriendToken(0);
		friendsService.clearFriendConfirmToken(friend);
		friendsService.clearFriendToken(user);
		friendsService.clearFriendConfirmToken(user);
		friendsService.clearFriendToken(friend);
		
		System.out.println("Inside remove friends");
		System.out.println("Friend: "+friend.getId());
		System.out.println("Friend's friend token"+friend.getFriendToken());
		System.out.println("Friend's confirmation token" +friend.getFriendConfirmationToken());
		System.out.println("User: "+user.getId());
		System.out.println("User's friend token"+user.getFriendToken());
		System.out.println("User's confirmation token "+user.getFriendConfirmationToken());
		friendsService.removeFriend(friend);
		friendsService.removeFriendUser(friend);
		
		
		modelAndView.addObject("user", friend);
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
		friendsService.updateFriendToken(friend);
		friendsService.updateConfirmToken(friend);
		friendsService.addFriend(friend, user);
		CONFIRM_FRIEND_TOKEN_VALUE = 1;
		friend.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		user.setFriendConfirmationToken(CONFIRM_FRIEND_TOKEN_VALUE);
		friend.setFriendToken(CONFIRM_FRIEND_TOKEN_VALUE);
		System.out.println("Inside confirm friends");
		System.out.println("Friend: "+friend.getId());
		System.out.println(friend.getId()+" friend token"+friend.getFriendToken());
		System.out.println(friend.getId() + " confirmation token" +friend.getFriendConfirmationToken());
		System.out.println("User: "+user.getId());
		System.out.println(user.getId()+" friend token"+user.getFriendToken());
		System.out.println(user.getId()+" confirmation token "+user.getFriendConfirmationToken());
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
		friendsService.addFriend(friend, user);
		FRIEND_TOKEN_VALUE = 1;
		friend.setFriendToken(FRIEND_TOKEN_VALUE);
		System.out.println("Inside add friends");
		System.out.println("Friend: "+friend.getId());
		System.out.println(friend.getId()+ " friend token"+friend.getFriendToken());
		System.out.println(friend.getId()+" confirmation token" +friend.getFriendConfirmationToken());
		System.out.println("User: "+user.getId());
		System.out.println(user.getId() +" friend token"+user.getFriendToken());
		System.out.println(user.getId() +" confirmation token "+user.getFriendConfirmationToken());
		log.debug("Friend added");
		//all the posts related to this person must be deleted from timeline
		return "redirect:friends";
	}


}
