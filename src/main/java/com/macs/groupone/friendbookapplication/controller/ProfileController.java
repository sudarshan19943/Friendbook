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
import com.macs.groupone.friendbookapplication.validator.LoginValidator;
import com.macs.groupone.friendbookapplication.validator.ProfileValidator;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.StringUtils;

@Controller
public class ProfileController {

	private static final Logger log = Logger.getLogger(ProfileController.class);

	@Autowired
	UserService userService;

	@Autowired AvatarService avatarService;
	
	@Autowired
	private ProfileValidator profileValidator;

	@GetMapping("/profile")
	public String getProfile(Model model,HttpServletRequest request,RedirectAttributes redirect) {
		
		model.addAttribute("profileForm", new User());
		HttpSession session=request.getSession();
		String emailFromSession=(String) session.getAttribute("email");
		     if(emailFromSession==null)
		    	 return "redirect:login";
		     
		User user=userService.getUserByEmail(emailFromSession) ;   
		model.addAttribute("fullName", user.getFirstName()+" "+user.getLastName());
		model.addAttribute("city", user.getCityId());
		if(user.getUserImage()==null)
		{//show default image
			//model.addAttribute("avatarpic","../../avatarImages/avatar.png");
			model.addAttribute("avatarpic",AvatarService.getDefaultAvatarImage());
		}else
		{
			model.addAttribute("avatarpic",user.getUserImage());
		}
		System.out.println(user.getUserImage());
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
				HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic,RedirectAttributes redirect) {
				String emailFromSession=(String) request.getSession().getAttribute("email");
				if(emailFromSession==null)
					return "redirect:login";
				
				User findUserFromEmail=userService.getUserByEmail(emailFromSession) ; 
				if(!StringUtils.isNullOrEmpty(profileForm.getCityId()))
				{
					findUserFromEmail.setCityId(profileForm.getCityId());
				}
				if(!StringUtils.isNullOrEmpty(profileForm.getStateId()))
				{
					findUserFromEmail.setCountryId(profileForm.getStateId());
				}
				if(!StringUtils.isNullOrEmpty(profileForm.getCountryId()))
				{
					findUserFromEmail.setStateId(profileForm.getCountryId());
				}
				if(null!=profilepic && !StringUtils.isNullOrEmpty(profilepic.getOriginalFilename()))
				{
					 System.out.println("Profile Pic Size"+profilepic.getSize());
					//if image size exceed
					 if(profilepic.getSize()>1048576)
					 {
					   redirect.addFlashAttribute("errorMessage","Image Size exceeded, chose image less than 20 KB");
					   //model.addAttribute("errorMessage","Image Size exceeded, chose image less than 20 KB");
					 //redirect.addFlashAttribute("errorMessage","Image Size exceeded.");
					   return "redirect:/profile";
					 }else
					 {
						 avatarService.uploadAvatarAndSaveBLOB(profilepic,findUserFromEmail.getEmail());
					 }
					//check for image size
				}
				userService.updateUserLocation(findUserFromEmail);
				//request.getSession().setAttribute("user", findUserFromEmail);
				log.info("User Profile has been successfully updated.");
			return "redirect:timeline";
		}
		
		
		// Going to reset page without a token redirects to login page
		@ExceptionHandler(MissingServletRequestParameterException.class)
		public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
			return new ModelAndView("redirect:login");
		}
		
}
		
		
		
		


