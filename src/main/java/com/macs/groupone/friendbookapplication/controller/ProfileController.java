package com.macs.groupone.friendbookapplication.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.UserService;

@Controller
public class ProfileController {

	private static final Logger log = Logger.getLogger(ProfileController.class);

	@Autowired
	UserService userService;

	@Autowired AvatarService avatarService;
	 

	@GetMapping("/profile")
	public String getProfile(Model model,HttpServletRequest request,RedirectAttributes redirect) {
		model.addAttribute("profileForm", new User());
		HttpSession session=request.getSession();
		User sessionUser=(User) session.getAttribute("user");
		model.addAttribute("fullName", sessionUser.getFirstName()+" "+sessionUser.getLastName());
		model.addAttribute("city", sessionUser.getCityId());
		model.addAttribute("avatarpic",AvatarService.getProfileAvatar(sessionUser.getEmail()));
		return "profile";
	}


	@PostMapping(params = "Skip")
	public String skipProfileUpdate(Model model, @ModelAttribute("profileForm") User profileForm, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic) {
		//do nothing
		log.info("Skipping Profile Update.");
		return "redirect:timeline";
	}
	
	    // update Profile
	   @PostMapping(params = "Update")
		public String updateProfile(Model model, @ModelAttribute("profileForm") User profileForm, BindingResult bindingResult,
				HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic) {
				User sessionUser=(User) request.getSession().getAttribute("user");
				if(sessionUser==null)
					return "redirect:login";
				sessionUser.setCityId(profileForm.getCityId());
				sessionUser.setCountryId(profileForm.getCountryId());
				sessionUser.setStateId(profileForm.getStateId());
				userService.updateUserLocation(sessionUser);
				avatarService.uploadAvatarAndSave(profilepic,sessionUser.getEmail());
				request.getSession().setAttribute("user", sessionUser);
				log.info("User Profile has been successfully updated.");
			return "redirect:timeline";
		}
		
		
		// Going to reset page without a token redirects to login page
		@ExceptionHandler(MissingServletRequestParameterException.class)
		public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
			return new ModelAndView("redirect:login");
		}
		
}
		
		
		
		


