package com.macs.groupone.friendbookapplication.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	 

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showDeafultProfilePage(Model model, ModelAndView modelAndView, HttpServletRequest request,
			RedirectAttributes redirect) {
		String email = (String) model.asMap().get("userEmail");
		String password = (String) model.asMap().get("password");
		User userByEmail = userService.getUserByEmailPassword(email, password);
		modelAndView.addObject("firstName", userByEmail.getFirstName());
		System.out.println("First Name" +userByEmail.getFirstName());
		modelAndView.addObject("lastName", userByEmail.getLastName());
		System.out.println("Last Name:" +userByEmail.getLastName());
		modelAndView.addObject("city", userByEmail.getCity());
		System.out.println("City:" +userByEmail.getCity());
		String pathHardCode="../../avatarImages/smn.singh666@gmail.com.JPG";
		System.out.println("pathHardCode : "+pathHardCode);
		modelAndView.addObject("avatarpic",pathHardCode);
		System.out.println("profile pic path : "+AvatarService.getProfileAvatar(userByEmail.getEmail()));
		modelAndView.setViewName("profile");
		return modelAndView;
	}


	    // Process login
		@RequestMapping(value = "/profile", method = RequestMethod.POST)
		public ModelAndView processLogin(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
				HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic,
	            RedirectAttributes redirectAttributes) {
			userService.updateUser(user);
			avatarService.uploadAvatarAndSave(profilepic,user.getEmail());
			modelAndView.setViewName(Constants.PROFILE_VIEW);
			return modelAndView;
		}
		
		
		
		

}
