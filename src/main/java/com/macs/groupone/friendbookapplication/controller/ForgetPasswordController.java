package com.macs.groupone.friendbookapplication.controller;

import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.EmailService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class ForgetPasswordController {
	
	private static final Logger log = LoggerFactory.getLogger(ForgetPasswordController.class);

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	// show password page
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public void showForgetPassword(ModelAndView modelAndView) {
		modelAndView.setViewName(Constants.FORGOTPASSWORD_VIEW);
	}

	// Forget Password POST Request
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public @ResponseBody ModelAndView processForgetPassword(ModelAndView modelAndView,
			@RequestParam("email") String userEmail, HttpServletRequest request) throws MessagingException {

		User user = userService.getUserByEmail(userEmail);
		if (user == null) {
			modelAndView.addObject(Constants.ERRORMESSAGE, Constants.ACCOUNT_NOT_FOUND);
		} else {
			user.setConfirmationToken(UUID.randomUUID().toString());
			userService.updateUser(user);
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			String message = "To reset your password, click the link below:\n" + appUrl + "/reset?token="
					+ user.getConfirmationToken();
			emailService.sendEmail(user.getEmail(), Constants.EMAIL_TITLE, message);
			modelAndView.addObject(Constants.SUCCESSMESSAGE, Constants.PASSWORD_LINK_SENT + userEmail);
		}

		modelAndView.setViewName("forgotpassword");
		return modelAndView;
	}
}
