package com.macs.groupone.friendbookapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.macs.groupone.friendbookapplication.constants.Constants;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	// show login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	// Login process
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView processLogin(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {
		User userByEmail= userService.getUserByEmailPassword(user.getEmail(), user.getPassword());
		if (userByEmail == null) {
			modelAndView.addObject(Constants.ERRORMESSAGE, Constants.USER_DOES_NOT_EXISTS);
			modelAndView.setViewName(Constants.LOGIN_VIEW);
		} else {
			modelAndView.setViewName(Constants.PROFILE_VIEW);
		}
		return modelAndView;
	}

}
