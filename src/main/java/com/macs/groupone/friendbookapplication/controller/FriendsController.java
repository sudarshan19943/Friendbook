package com.macs.groupone.friendbookapplication.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AddFriendStateService;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.ConfirmFriendStateService;
import com.macs.groupone.friendbookapplication.service.FriendsService;
import com.macs.groupone.friendbookapplication.service.MessageService;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.RemoveFriendStateService;
import com.macs.groupone.friendbookapplication.service.StateContextService;
import com.macs.groupone.friendbookapplication.service.StateFactoryService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class FriendsController {

	final static Logger logger = Logger.getLogger(FriendsController.class);
	private boolean enableConfirmButton = false;
	private boolean enableRemoveButton = false;
	
	
	UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
	FriendsService friendsService=(FriendsService) ServiceFactory.getInstance().getFriendService();
	AvatarService avatarService=(AvatarService)ServiceFactory.getInstance().getAvatarService();
	MessageService messageService=(MessageService)ServiceFactory.getInstance().getMessageService();


	private static final Logger log = Logger.getLogger(FriendsController.class);
	
	private AddFriendStateService addFriendState = (AddFriendStateService)StateFactoryService.getInstance().addFriendState();
	private ConfirmFriendStateService confirmFriendState = (ConfirmFriendStateService)StateFactoryService.getInstance().confirmFriendState();
	private RemoveFriendStateService removeFriendState = (RemoveFriendStateService)StateFactoryService.getInstance().removeFriendState();

	private User getUserFromSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		User user = userService.getUserByEmail(emailfromsession);
		return user;
	}
	
	@GetMapping(value = "/friends")
	public ModelAndView showFriendPage(Model model, ModelAndView modelAndView, RedirectAttributes redirect, HttpServletRequest request) 
	{
		User user = getUserFromSession(request);
		ArrayList<User> friendList=(ArrayList<User>) userService.findFriendsFromDatabase(user);

		friendsService.removeUserFromFriendsList(user, friendList);
		getButtonState(user);

		model.addAttribute("avatarpic",user.getUserImage());	
		model.addAttribute("usersForm", new User());
		modelAndView.addObject("friends", friendList);
		model.addAttribute("enableConfirmButton", enableConfirmButton);
		model.addAttribute("enableRemoveButton", enableRemoveButton);

		return modelAndView;
	}

	@PostMapping(value = "/friends", params = "findFriends")
	public ModelAndView findFriends(Model model, ModelAndView modelAndView, RedirectAttributes redirect, @ModelAttribute("usersForm") User usersForm, HttpServletRequest request) 
	{
		User user = getUserFromSession(request);

		ArrayList<User> userList=(ArrayList<User>) userService.findUsers(usersForm);
		ArrayList<User> friendList=(ArrayList<User>) userService.findFriendsFromDatabase(user);
		friendsService.removeUserFromFriendsList(user, friendList);
		friendsService.removeUserFromFriendsList(user, userList);
		friendsService.removeFriendsfromUserList(userList, friendList);
		getButtonState(user);
		model.addAttribute("enableConfirmButton", enableConfirmButton);
		model.addAttribute("enableRemoveButton", enableRemoveButton);
		modelAndView.addObject("friends", friendList);
		modelAndView.addObject("users", userList);
		model.addAttribute("avatarpic",user.getUserImage());
		return modelAndView;

	}

	@PostMapping(value = "/removefriends", params= "removeFriends")
	public String deleteFriend(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, HttpServletRequest request, @ModelAttribute("removefriendsForm") User friend, @RequestParam("removeFriends") String post) 
	{

		friend.setId(Integer.parseInt(post));

		User user = getUserFromSession(request);
		StateContextService  context = new StateContextService (removeFriendState);
		context.executeState(friend, user);

		log.debug("Friend removed");
		return Constants.REDIRECT_FRIENDS;
	}

	@PostMapping(value = "/confirmfriend", params = "confirmfriend")
	public String confirmFriend(Model model, ModelAndView modelAndView,
			RedirectAttributes redirect, HttpServletRequest request, @RequestParam("confirmfriend") String confirmfriend, @ModelAttribute("confirmfriendForm") User friend)
	{

		friend.setId(Integer.parseInt(confirmfriend));
		User user = getUserFromSession(request);
		StateContextService  context = new StateContextService (confirmFriendState);
		context.executeState(friend, user);

		log.debug("Friend confirmed");
		return Constants.REDIRECT_FRIENDS;
	}

	@PostMapping(value = "/addfriends", params = "addFriends")
	public String addFriend(Model model, ModelAndView modelAndView, RedirectAttributes redirect, HttpServletRequest request, @RequestParam("addFriends") String addfriends, @ModelAttribute("addfriendsForm") User friend) 
	{

		friend.setId(Integer.parseInt(addfriends));
		User user = getUserFromSession(request);
		StateContextService  context = new StateContextService (addFriendState);
		context.executeState(friend, user);

		log.debug("Friend added");
		return Constants.REDIRECT_FRIENDS;
	}

	private void getButtonState(User user)
	{
		if(user.getFriendToken() && !(user.getFriendConfirmationToken()))
		{
			enableConfirmButton = true;
			enableRemoveButton = false;
		}
		else if(user.getFriendConfirmationToken() && !(user.getFriendToken()))
		{
			enableConfirmButton = false;
			enableRemoveButton = true;
		}
	}
}
