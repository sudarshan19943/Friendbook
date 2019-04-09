package com.macs.groupone.friendbookapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.FormValidatorFactory;
import com.macs.groupone.friendbookapplication.validator.LoginValidator;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	private LoginValidator loginValidator = FormValidatorFactory.getInstance().getLoginValidator();

	@GetMapping("/login")
	public String displayLogin(Model model, HttpSession session) {
		model.addAttribute(Constants.LOGIN_FORM, new User());
		return Constants.LOGIN_VIEW;
	}

	@PostMapping("/login")
	public String processLogin(Model model, @ModelAttribute("loginForm") User loginForm, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirect) {
		loginValidator.validate(loginForm, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.debug("Login Form has validation errors");
			return Constants.LOGIN_VIEW;
		} else {
			UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
			User userByEmailAndPassword = userService.getUserByEmailPassword(loginForm.getEmail(),
					loginForm.getPassword());
			if (userByEmailAndPassword != null) {
				HttpSession session = request.getSession();
				session.setAttribute(Constants.EMAIL, loginForm.getEmail());
				logger.debug("User" + userByEmailAndPassword.getFirstName() + "," + userByEmailAndPassword.getLastName()
						+ "has been logged sucessfully");
				return Constants.REDIRECT_TIMELINE;
			} else {
				model.addAttribute(Constants.ERRORMESSAGE, Constants.PASSWORD_DOES_NOT_MATCH);
				return Constants.LOGIN_VIEW;
			}
		}

	}

	@GetMapping(value = "/logout")
	public String processSignOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		session.invalidate();
		logger.debug("session id after invalidating session is:" + session.getId());
		return Constants.REDIRECT_LOGIN;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {
		return Constants.REDIRECT_LOGIN;
	}
}
