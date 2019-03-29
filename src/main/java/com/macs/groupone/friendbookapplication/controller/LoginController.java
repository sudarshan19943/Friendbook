package com.macs.groupone.friendbookapplication.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.LoginValidator;

@Controller
public class LoginController {

	private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	private LoginValidator loginValidator;

	@GetMapping("/login")
	public String registration(Model model) {
		model.addAttribute("loginForm", new User());
		return "login";
	}

	@PostMapping("/login")
	public String registration(Model model, @ModelAttribute("loginForm") User loginForm, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirect) {
		loginValidator.validate(loginForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
				User userByEmailAndPassword = userService.getUserByEmailPassword(loginForm.getEmail(),
						loginForm.getPassword());
				if (userByEmailAndPassword != null) {
					redirect.addFlashAttribute("email", userByEmailAndPassword.getEmail());
					redirect.addFlashAttribute("firstName", userByEmailAndPassword.getFirstName());
					redirect.addFlashAttribute("lastName", userByEmailAndPassword.getLastName());
					redirect.addFlashAttribute("password", loginForm.getPassword());
					return "redirect:/profile";
				} else {
					model.addAttribute(Constants.ERRORMESSAGE, Constants.PASSWORD_DOES_NOT_MATCH);
					return "login";
				}
			}

		}
	}

