package com.macs.groupone.friendbookapplication.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.AvatarService;
import com.macs.groupone.friendbookapplication.service.EmailService;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.FormValidatorFactory;
import com.macs.groupone.friendbookapplication.validator.RegistrationValidator;

@Controller
public class RegistrationController 
{

	private static final Logger log = Logger.getLogger(RegistrationController.class);
    
	AvatarService avatarService=(AvatarService)ServiceFactory.getInstance().getAvatarService();
    private RegistrationValidator registrationValidator=FormValidatorFactory.getInstance().getRegistrationValidator();
	

	@GetMapping("/registration")
	public String registration(Model model) 
	{
		model.addAttribute("registrationForm", new User());
		return Constants.REGISTER_VIEW;
	}
	
	@PostMapping("/registration")
	public String processRegistration(Model model, @ModelAttribute("registrationForm") User registrationForm, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirect) 
	{
		registrationValidator.validate(registrationForm, bindingResult);
		if (bindingResult.hasErrors()) 
		{
			return Constants.REGISTER_VIEW;
		} 
		else 
		{
			try 
			{
					UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
					userService.addUser(registrationForm.getEmail(), registrationForm.getPassword(), registrationForm.getFirstName(), registrationForm.getLastName());
					EmailService emailService = (EmailService) ServiceFactory.getInstance().getEmailService();
					emailService.sendEmail(registrationForm.getEmail(), Constants.REGISTRATION_CONFIRMATION_TITLE,Constants.REGISTRATION_CONFIRMATION_MESSAGE);
					avatarService.saveDefaultAvatar(registrationForm.getEmail());
					request.getSession().setAttribute(Constants.EMAIL, registrationForm.getEmail());
					return Constants.REDIRECT_PROFILE;
			}
			catch (MessagingException e) 
			{
				e.printStackTrace();
			}
		}
		return Constants.LOGIN_VIEW;
		}
}
