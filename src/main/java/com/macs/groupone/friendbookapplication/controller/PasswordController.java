package com.macs.groupone.friendbookapplication.controller;

import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class PasswordController {
	private String RESET_VIEW="resetpassword";
	private String FORGOTPASSWORD_VIEW="forgotpassword";
	private String ERRORMESSAGE="errorMessage";
	private String INVALID_PASSWORD_LINK="Oops!  This is an invalid password reset link.";
	private String SUCCESSMESSAGE="successMessage";
	private String PASSWORD_RESET_SUCCESS="You have successfully reset your password.  You may now login.";
	@Autowired
	UserService userService;


	// show password page
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView showForgetPassword(ModelAndView modelAndView) {
		modelAndView.setViewName(FORGOTPASSWORD_VIEW);
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

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView processResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

		User user = userService.findUserByResetToken(token);
		if (user != null) {
			modelAndView.addObject("resetToken", token);
		} else {
			modelAndView.addObject(ERRORMESSAGE,INVALID_PASSWORD_LINK);
		}
		modelAndView.setViewName(RESET_VIEW);
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
			redir.addFlashAttribute(SUCCESSMESSAGE, PASSWORD_RESET_SUCCESS);
			modelAndView.setViewName("redirect:login");
			return modelAndView;
		} else {
			modelAndView.addObject(ERRORMESSAGE, INVALID_PASSWORD_LINK);
			modelAndView.setViewName(RESET_VIEW);
		}
		return modelAndView;
	}
}