package com.macs.groupone.friendbookapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class LoginController {
	
	  private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	// show login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "login";
	}

	// Process login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(Model model,ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request,RedirectAttributes redirect) {
		User userByEmail= userService.getUserByEmailPassword(user.getEmail(), user.getPassword());
		if (userByEmail == null) {
			modelAndView.addObject(Constants.ERRORMESSAGE,Constants.USER_DOES_NOT_EXISTS);
			modelAndView.setViewName(Constants.LOGIN_VIEW);
			return "login";  //need to check if it is not getting into loop
		} else {
			redirect.addFlashAttribute("userEmail",user.getEmail());
			log.debug("User email:" +user.getEmail());
			redirect.addFlashAttribute("password",user.getPassword());
			return "redirect:profile";
		}
	}

}
