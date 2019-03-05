package com.macs.groupone.friendbookapplication.controller;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	UserService userService;

	@Autowired
	CountryAndStateService countryAndStateService;

	@Autowired AvatarService avatarService;
	 

	@RequestMapping(value = "/profileValueLoader", method = RequestMethod.GET)
	public ModelAndView showDeafultProfilePage(Model model, ModelAndView modelAndView, HttpServletRequest request,
			RedirectAttributes redirect) {
		String email = (String) model.asMap().get("userEmail");
		String password = (String) model.asMap().get("password");
		User userByEmail = userService.getUserByEmailPassword(email, password);
		modelAndView.addObject("firstName", userByEmail.getFirstName());
		modelAndView.addObject("lastName", userByEmail.getLastName());
		ArrayList<String> countryList = countryAndStateService.getListOfCountries(Locale.ENGLISH);
		modelAndView.addObject("Countries", countryList);
		modelAndView.addObject("city", userByEmail.getCity());
		AvatarService.getProfileAvatar(userByEmail.getId());
		return modelAndView;
	}

	// show login page
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showDeafultProfilePage(ModelAndView modelAndView ,RedirectAttributes redir) {
		
		Map<String,String> UserDetails=(Map<String, String>) redir.getFlashAttributes();
		String email=UserDetails.get("userEmail");
		String password=UserDetails.get("password");
		User userByEmail= userService.getUserByEmailPassword(email,password);
		modelAndView.addObject("first_name",userByEmail.getFirstName());
		modelAndView.addObject("last_name",userByEmail.getLastName());
		
		//country list
		ArrayList<String> countryList=countryAndStateService.getListOfCountries(Locale.ENGLISH);
		modelAndView.addObject("Countries",countryList);
		
		//initially default avatar
		//avatarService.getDeafultProfileAvatar(userByEmail.getId());

		modelAndView.setViewName("profile");
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
