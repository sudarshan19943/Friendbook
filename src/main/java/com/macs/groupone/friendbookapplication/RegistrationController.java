package com.macs.groupone.friendbookapplication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.macs.groupone.friendbookapplication.constants.Constants;
import com.macs.groupone.model.User;
import com.macs.groupone.service.EmailService;
import com.macs.groupone.service.UserService;


public class RegistrationController {
	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	// show registration
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showSignUpPage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName(Constants.REGISTER_VIEW);
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
			BindingResult bindingResult, HttpServletRequest request) {

		User userExists = userService.getUserByEmail(user.getEmail());
		if (userExists != null) {
			modelAndView.addObject(Constants.ALREADY_REGISTERED,Constants.ALREADY_REGISTERED_ERROR);
			modelAndView.setViewName(Constants.REGISTER_VIEW);
			//bindingResult.reject("email");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName(Constants.REGISTER_VIEW);
		} else {
			userService.addUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
			modelAndView.setViewName(Constants.PROFILE_VIEW);
		}

		return modelAndView;
	}

}
