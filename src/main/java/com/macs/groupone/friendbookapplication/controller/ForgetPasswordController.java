package com.macs.groupone.friendbookapplication.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.EmailService;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.ForgetPasswordValidator;
import com.macs.groupone.friendbookapplication.validator.ResetPasswordValidator;

@Controller
public class ForgetPasswordController {

	final static Logger logger = Logger.getLogger(ForgetPasswordController.class);

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@Autowired
	private ForgetPasswordValidator forgetPasswordValidator;
	
	@Autowired
	private ResetPasswordValidator resetPasswordValidator;


    @GetMapping("/forgotpassword")
    public String registration(Model model) {
        model.addAttribute("forgotPasswordForm", new User());
        return "forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String registration(Model mode,@ModelAttribute("forgotPasswordForm") User forgotPasswordForm, BindingResult bindingResult,HttpServletRequest request) {
    	forgetPasswordValidator.validate(forgotPasswordForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "forgotpassword";
        }
       else {
			User user = userService.getUserByEmail(forgotPasswordForm.getEmail());
			user.setConfirmationToken(UUID.randomUUID().toString());
			user.setEnabled(true);
			userService.updateUser(user);
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			String message = "To reset your password, click the link below:\n" + appUrl + "/resetpassword?token="
					+ user.getConfirmationToken();
			try {
			emailService.sendEmail(user.getEmail(), "Friend Book Registration Confirmation", message);
			mode.addAttribute("successMessage", "Reset link has been mailed to your registered mail id.");
			}
			catch(Exception e )
			{
				e.printStackTrace();
			}
			logger.debug("Email sent");
			return "redirect:/login";
		}

    }
  
    
	private static String tokenVal = null;

	// Display form to reset password
	@GetMapping(value = "/resetpassword")
	public String displayResetPasswordPage(Model model,@ModelAttribute("resetPasswordForm") User resetPasswordForm, @RequestParam("token") String token,
			BindingResult bindingResult) {
		User user = userService.findUserByResetToken(token);
		if (user != null) {
			tokenVal = token;
		} else {
			model.addAttribute("errorMessage",  "Oops!  This is an invalid password reset link.");
		}
		return "resetpassword";
	}

	// Process reset password form
	
	@PostMapping("/resetpassword")
    public String setNewPassword(Model model,@ModelAttribute("resetPasswordForm") User resetPasswordForm, 
    		BindingResult bindingResult,HttpServletRequest request, RedirectAttributes redirect) {
		resetPasswordValidator.validate(resetPasswordForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "resetpassword";
        }
       else {
    	   User user = userService.findUserByResetToken(tokenVal);
   		if (user != null) {
   			User resetUser = user;
   			resetUser.setPassword(resetPasswordForm.getPassword());
   			resetUser.setConfirmationToken(null);
   			resetUser.setEnabled(false);
   			userService.resetUserPassword(resetUser);
   			redirect.addFlashAttribute("email", resetUser.getEmail());
			redirect.addFlashAttribute("firstName", resetUser.getFirstName());
			redirect.addFlashAttribute("lastName", resetUser.getLastName());
			redirect.addFlashAttribute("password", resetUser.getPassword());
   			//model.addAttribute("successMessage", "You have successfully reset your password.  You may now login.");
   			return "redirect:/profile";
   		} else {
   			model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
   			return "redirect:/resetpassword";
   		}
		}

    }
	
	/*// Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:login");
	}*/
}
