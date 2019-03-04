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
public class PasswordController {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordController.class);

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

	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView processResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

		User user = userService.findUserByResetToken(token);
		if (user != null) {
			modelAndView.addObject("resetToken", token);
		} else {
			modelAndView.addObject(Constants.ERRORMESSAGE, Constants.INVALID_PASSWORD_LINK);
		}
		modelAndView.setViewName(Constants.RESET_VIEW);
		return modelAndView;
	}

	// Process reset password form
	@RequestMapping(value = "/reset/{token}", method = RequestMethod.POST)
	public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams,
			@RequestParam("token") String token, RedirectAttributes redir) {

		User user = userService.findUserByResetToken(token);
		if (user != null) {
			user.setPassword(requestParams.get("password"));
			user.setConfirmationToken(null);
			userService.updateUser(user);
			redir.addFlashAttribute(Constants.SUCCESSMESSAGE, Constants.PASSWORD_RESET_SUCCESS);
			modelAndView.setViewName("redirect:login");
			return modelAndView;
		} else {
			modelAndView.addObject(Constants.ERRORMESSAGE, Constants.INVALID_PASSWORD_LINK);
			modelAndView.setViewName(Constants.RESET_VIEW);
		}
		return modelAndView;
	}
}
