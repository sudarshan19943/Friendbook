package com.macs.groupone.friendbookapplication.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.EmailService;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.RegistrationValidator;

@Controller
public class RegistrationController {

	private static final Logger log = Logger.getLogger(RegistrationController.class);

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;
	
	@Autowired
    private RegistrationValidator registrationValidator;


	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("registrationForm", new User());
		return "registration";
	}

	
	@PostMapping("/registration")
	public String processRegistration(Model model, @ModelAttribute("registrationForm") User registrationForm, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirect) {
		registrationValidator.validate(registrationForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		} else {
			try {
				if (emailService.sendEmail(registrationForm.getEmail(), Constants.EMAIL_TITLE,
						"You have been Registered with Friendbook.")) {
					model.addAttribute(Constants.ERRORMESSAGE, Constants.ACCOUNT_NOT_FOUND);
					return "registration"; 
				} else {
					userService.addUser(registrationForm.getEmail(), registrationForm.getPassword(), registrationForm.getFirstName(), registrationForm.getLastName());
					//model.addAttribute(Constants.SUCCESSMESSAGE, Constants.REGISTRATIONSUCCESS);
					redirect.addFlashAttribute("email", registrationForm.getEmail());
					redirect.addFlashAttribute("firstName", registrationForm.getFirstName());
					redirect.addFlashAttribute("lastName", registrationForm.getLastName());
					redirect.addFlashAttribute("password", registrationForm.getPassword());
					return "redirect:/profile";
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return "login";

		}
	
	
	 // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:registration");
	}

}
