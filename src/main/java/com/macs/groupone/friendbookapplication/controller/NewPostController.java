package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.FriendsService;

@Controller
public class NewPostController {
	/*
	 * @RequestMapping(value = "/newpost", method = RequestMethod.GET) public
	 * ModelAndView showSignUpPage(ModelAndView modelAndView, User user) {
	 * modelAndView.addObject("user", user);
	 * modelAndView.setViewName(Constants.NEW_POST_VIEW); return modelAndView; }
	 * 
	 * @Autowired FriendsService friendsService;
	 * 
	 * @RequestMapping(value = "/newpost", method = RequestMethod.POST) public
	 * ModelAndView processPost(ModelAndView modelAndView, @Valid User user,
	 * BindingResult bindingResult, HttpServletRequest request, RedirectAttributes
	 * redir) { Collection<User> friends = friendsService.getFriendsList(user); if
	 * (userByEmail == null) { modelAndView.addObject(Constants.ERRORMESSAGE,
	 * Constants.USER_DOES_NOT_EXISTS);
	 * modelAndView.setViewName(Constants.LOGIN_VIEW); } else {
	 * redir.addFlashAttribute("userEmail", user.getEmail());
	 * redir.addFlashAttribute("password", user.getPassword());
	 * modelAndView.setViewName("redirect:profile"); } return modelAndView; }
	 */
	 
}
