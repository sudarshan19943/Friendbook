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
	 

	/*@GetMapping("/profile")
	public String showDeafultProfilePage(Model model, HttpServletRequest request,
			RedirectAttributes redirect) {
		String email = (String) model.asMap().get("email");
		String firstName = (String) model.asMap().get("firstName");
		String lastName = (String) model.asMap().get("lastName");
		User user=userService.getUserByEmail(email);
		model.addAttribute("fullName", firstName+" "+lastName);
		model.addAttribute("city", user.getCityId());
		String pathHardCode="../../avatarImages/smn.singh666@gmail.com.JPG";
		System.out.println("pathHardCode : "+pathHardCode);
		model.addAttribute("avatarpic",pathHardCode);
		System.out.println("profile pic path : "+AvatarService.getProfileAvatar(email));
		return "profile";
	}*/
	
	@GetMapping("/profile")
	public String getProfile(Model model,HttpServletRequest request,RedirectAttributes redirect) {
		model.addAttribute("profileForm", new User());
		HttpSession session=request.getSession();
		String emailfromsession=(String) session.getAttribute("email");
		String email = (String) model.asMap().get("email");
		String password = (String) model.asMap().get("password");
		User userByEmail = userService.getUserByEmailPassword(email, password);
		model.addAttribute("fullName", userByEmail.getFirstName()+" "+userByEmail.getLastName());
		System.out.println("First Name" +userByEmail.getFirstName());
		model.addAttribute("lastName", userByEmail.getLastName());
		System.out.println("Last Name:" +userByEmail.getLastName());
		model.addAttribute("city", userByEmail.getCityId());
		String pathHardCode="../../avatarImages/smn.singh666@gmail.com.JPG";
		System.out.println("pathHardCode : "+pathHardCode);
		model.addAttribute("avatarpic",AvatarService.getProfileAvatar(userByEmail.getEmail()));
		System.out.println("profile pic path : "+AvatarService.getProfileAvatar(userByEmail.getEmail()));
		return "profile";
	}


	/*@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showDeafultProfilePage(Model model, ModelAndView modelAndView, HttpServletRequest request,
			RedirectAttributes redirect) {
		String email = (String) model.asMap().get("email");
		String password = (String) model.asMap().get("password");
		User userByEmail = userService.getUserByEmailPassword(email, password);
		modelAndView.addObject("fullName", userByEmail.getFirstName()+" "+userByEmail.getLastName());
		System.out.println("First Name" +userByEmail.getFirstName());
		modelAndView.addObject("lastName", userByEmail.getLastName());
		System.out.println("Last Name:" +userByEmail.getLastName());
		modelAndView.addObject("city", userByEmail.getCityId());
		String pathHardCode="../../avatarImages/smn.singh666@gmail.com.JPG";
		System.out.println("pathHardCode : "+pathHardCode);
		//modelAndView.addObject("avatarpic","../../avatarImages/avatar.png");
		modelAndView.addObject("avatarpic",AvatarService.getProfileAvatar(userByEmail.getEmail()));
		System.out.println("profile pic path : "+AvatarService.getProfileAvatar(userByEmail.getEmail()));
		modelAndView.setViewName("profile");
		return modelAndView;
	}*/

	/*@PostMapping(params = "Update")
	public String updateUserProfile(Model model, @ModelAttribute("profileForm") User profileForm, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic) {
		
		return Constants.TIMELINE_VIEW;
	}*/
	
	@RequestMapping(params = "Skip", method = RequestMethod.POST)
	public String skipProfileUpdate(Model model, @ModelAttribute("profileForm") User profileForm, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic) {
		//do nothing
		log.info("Skipping Profile Update.");
		return Constants.TIMELINE_VIEW;
	}
	
	    // update Profile
	   @PostMapping(params = "Update")
		public String updateProfile(Model model, @ModelAttribute("profileForm") User profileForm, @Valid User user, BindingResult bindingResult,
				HttpServletRequest request,@RequestParam("profilepic") MultipartFile profilepic) {
				userService.updateUser(user);
				avatarService.uploadAvatarAndSave(profilepic,user.getEmail());
				log.info("User Profile has been successfully updated.");
			return Constants.TIMELINE_VIEW;
		}
		
		
		// Going to reset page without a token redirects to login page
		@ExceptionHandler(MissingServletRequestParameterException.class)
		public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
			return new ModelAndView("redirect:login");
		}
		
}
		
		
		
		


