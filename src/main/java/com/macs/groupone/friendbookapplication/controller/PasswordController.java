package com.macs.groupone.friendbookapplication.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.macs.groupone.friendbookapplication.constants.Constants;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class PasswordController {

	@Autowired
	UserService userService;


	// show password page
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView showForgetPassword(ModelAndView modelAndView) {
		modelAndView.setViewName(Constants.FORGOTPASSWORD_VIEW);
		return modelAndView;
	}

	// Send a POST request for forgot password
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public @ResponseBody ModelAndView processForgetPassword(ModelAndView modelAndView,
		@RequestParam("email") String userEmail, HttpServletRequest request) throws MessagingException {

		User user = userService.getUserByEmail(userEmail);

		userService.updateUser(user);

		modelAndView.setViewName("forgotPassword");
		return modelAndView;
	}

	// Reset password page
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView processResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
		return modelAndView;
		//TODO: Implement reset password
	}
}