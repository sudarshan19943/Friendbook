package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.CountryAndStateService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class ProfileController {

	private static final Logger log = Logger.getLogger(ProfileController.class);

	@Autowired
	UserService userService;

	@Autowired
	CountryAndStateService countryAndStateService;

	@Autowired AvatarService avatarService;
	 

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showDeafultProfilePage(Model model, ModelAndView modelAndView, HttpServletRequest request,
			RedirectAttributes redirect) {
		String email = (String) model.asMap().get("userEmail");
		String password = (String) model.asMap().get("password");
		User userByEmail = userService.getUserByEmailPassword(email, password);
		modelAndView.addObject("first_name", userByEmail.getFirstName());
		log.debug("Country List:" +userByEmail.getFirstName());
		modelAndView.addObject("last_name", userByEmail.getLastName());
		log.debug("Country List:" +userByEmail.getLastName());
		ArrayList<String> countryList = countryAndStateService.getListOfCountries(Locale.ENGLISH);
		log.debug("Country List:" +countryList);
		modelAndView.addObject("Countries", countryList);
		modelAndView.addObject("city", userByEmail.getCity());
		log.debug("City:" +userByEmail.getCity());
		AvatarService.getProfileAvatar(userByEmail.getId());
		log.debug("Country List:" +countryList);
		modelAndView.setViewName(Constants.PROFILE_VIEW);
		return modelAndView;
	}


	// Process login
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView processLogin(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {
		userService.updateUser(user);
		modelAndView.setViewName(Constants.PROFILE_VIEW);
		return modelAndView;
	}

}
