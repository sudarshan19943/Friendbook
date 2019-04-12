package com.macs.groupone.friendbookapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.StringUtils;

@Controller
public class ProfileController {

	private static final Logger logger = Logger.getLogger(NewPostController.class);
	private static final int PROFILE_IMAGE_MAX_SIZE=1048576;

	UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
	AvatarService avatarService = (AvatarService) ServiceFactory.getInstance().getAvatarService();

	@GetMapping("/profile")
	public String displayProfile(Model model, HttpServletRequest request, RedirectAttributes redirect) {

		model.addAttribute("profileForm", new User());
		HttpSession session = request.getSession();
		String emailFromSession = (String) session.getAttribute("email");
		if (emailFromSession == null)
			return Constants.REDIRECT_LOGIN;

		User user = userService.getUserByEmail(emailFromSession);
		model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("city", user.getCityId());
		model.addAttribute("avatarpic", user.getUserImage());
		return Constants.PROFILE_VIEW;
	}

	@PostMapping(params = "Skip")
	public String skipProfileUpdate(Model model, @ModelAttribute("profileForm") User profileForm, @Valid User user,
			BindingResult bindingResult, HttpServletRequest request,
			@RequestParam("profilepic") MultipartFile profilepic) {
		logger.info("Skipping Profile Update.");
		return Constants.REDIRECT_TIMELINE;
	}

	@PostMapping(params = "Update")
	public String updateProfile(Model model, @ModelAttribute("profileForm") User profileForm,
			BindingResult bindingResult, HttpServletRequest request,
			@RequestParam("profilepic") MultipartFile profilepic, RedirectAttributes redirect) {
		String emailFromSession = (String) request.getSession().getAttribute("email");
		if (emailFromSession == null)
			return Constants.REDIRECT_LOGIN;

		User findUserFromEmail = userService.getUserByEmail(emailFromSession);
		if (!StringUtils.isNullOrEmpty(profileForm.getCityId())) {
			findUserFromEmail.setCityId(profileForm.getCityId());
		}
		if (!StringUtils.isNullOrEmpty(profileForm.getStateId())) {
			findUserFromEmail.setCountryId(profileForm.getStateId());
		}
		if (!StringUtils.isNullOrEmpty(profileForm.getCountryId())) {
			findUserFromEmail.setStateId(profileForm.getCountryId());
		}
		if (null != profilepic && !StringUtils.isNullOrEmpty(profilepic.getOriginalFilename())) {
			logger.error("Profile Pic Size" + profilepic.getSize());
			if (profilepic.getSize() > PROFILE_IMAGE_MAX_SIZE) {
				redirect.addFlashAttribute(Constants.ERRORMESSAGE, Constants.IMAGE_SIZE_EXCEEDED);
				return Constants.REDIRECT_PROFILE;
			} else {
				avatarService.uploadAvatarAndSaveBLOB(profilepic, findUserFromEmail.getEmail());
			}
		}
		userService.updateUserLocation(findUserFromEmail);
		logger.info("User Profile has been successfully updated.");
		return Constants.REDIRECT_TIMELINE;
	}


	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView(Constants.REDIRECT_LOGIN);
	}

}
