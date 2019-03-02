package com.macs.groupone.friendbookapplication.controller;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.macs.groupone.friendbookapplication.service.EmailService;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;


public class RegistrationController {
	private String ALREADY_REGISTERED="alreadyRegisteredMessage";
	private String ALREADY_REGISTERED_ERROR= "Oops!  There is already a user registered with the email provided.";
	private String REGISTER_VIEW="registration";
	private String PROFILE_VIEW="profile";
	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	// show registration
	@RequestMapping(value = "/registeration", method = RequestMethod.GET)
	public ModelAndView showSignUpPage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName(REGISTER_VIEW);
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/registeration", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
			BindingResult bindingResult, HttpServletRequest request) {

		User userExists = userService.getUserByEmail(user.getEmail());
		if (userExists != null) {
			modelAndView.addObject(ALREADY_REGISTERED,ALREADY_REGISTERED_ERROR);
			modelAndView.setViewName(REGISTER_VIEW);
			//bindingResult.reject("email");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName(REGISTER_VIEW);
		} else {
			userService.addUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
			modelAndView.setViewName(PROFILE_VIEW);
		}

		return modelAndView;
	}

}
