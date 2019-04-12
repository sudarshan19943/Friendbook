package com.macs.groupone.friendbookapplication.controller;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.EmailService;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.ForgetPasswordValidator;
import com.macs.groupone.friendbookapplication.validator.FormValidatorFactory;
import com.macs.groupone.friendbookapplication.validator.ResetPasswordValidator;

//References-
//https://www.codebyamir.com/blog/forgot-password-feature-with-java-and-spring-boot

@Controller
public class ForgetPasswordController {

	private static final Logger logger = Logger.getLogger(ForgetPasswordController.class);

	private UserService userService = (UserService) ServiceFactory.getInstance().getUserService();
	private ForgetPasswordValidator forgetPasswordValidator = FormValidatorFactory.getInstance()
			.getForgetPasswordValidator();
	private ResetPasswordValidator resetPasswordValidator = FormValidatorFactory.getInstance()
			.getResetPasswordValidator();

	@GetMapping("/forgotpassword")
	public String displayForgotPassword(Model model) {
		model.addAttribute(Constants.FORGOTPASSWORD_FORM, new User());
		return Constants.FORGOTPASSWORD_VIEW;
	}

	@PostMapping("/forgotpassword")
	public String processForgotPassword(Model mode, @ModelAttribute("forgotPasswordForm") User forgotPasswordForm,
			BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirect) {
		forgetPasswordValidator.validate(forgotPasswordForm, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.debug("Forget Password Form has validation errors");
			return Constants.FORGOTPASSWORD_VIEW;
		} else {
			User user = userService.getUserByEmail(forgotPasswordForm.getEmail());
			user.setConfirmationToken(UUID.randomUUID().toString());
			user.setEnabled(true);
			userService.updateUser(user);
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			String message = "To reset your password, click the link below:\n" + appUrl + "/resetpassword?token="
					+ user.getConfirmationToken();
			try {
				EmailService emailService = (EmailService) ServiceFactory.getInstance().getEmailService();
				emailService.sendEmail(user.getEmail(), Constants.RESET_PASSWORD_TITLE, message);
				redirect.addFlashAttribute(Constants.SUCCESSMESSAGE, Constants.RESET_PASSWORD_LINK_SENT);
			} catch (AddressException e) {
				logger.error("Exception occured while sending and email to User, Address is not be valid. "
						+ e.getMessage());
				e.printStackTrace();
			} catch (MessagingException e) {
				logger.error("Exception occured while sending and email to User. " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Exception occured while Registering new User " + e.getMessage());
				e.printStackTrace();
			}
			logger.debug("Email sent");
			return Constants.REDIRECT_LOGIN;
		}

	}

	private static String tokenVal = null;

	@GetMapping(value = "/resetpassword")
	public String displayResetPasswordPage(Model model, @ModelAttribute("resetPasswordForm") User resetPasswordForm,
			@RequestParam("token") String token, BindingResult bindingResult) {
		User user = userService.findUserByResetToken(token);
		if (user != null) {
			tokenVal = token;
		} else {
			model.addAttribute(Constants.ERRORMESSAGE, Constants.INVALID_PASSWORD_LINK);
		}
		return Constants.RESET_VIEW;
	}

	@PostMapping("/resetpassword")
	public String processNewPassword(Model model, @ModelAttribute("resetPasswordForm") User resetPasswordForm,
			BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirect) {
		resetPasswordValidator.validate(resetPasswordForm, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.debug("Reset Password Form has validation errors");
			return Constants.RESET_VIEW;
		} else {
			User user = userService.findUserByResetToken(tokenVal);
			if (user != null) {
				User resetUser = user;
				resetUser.setPassword(resetPasswordForm.getPassword());
				resetUser.setConfirmationToken(null);
				resetUser.setEnabled(false);
				userService.resetUserPassword(resetUser);
				redirect.addFlashAttribute(Constants.EMAIL, resetUser.getEmail());
				redirect.addFlashAttribute(Constants.SUCCESSMESSAGE, Constants.PASSWORD_RESET_SUCCESS);
				logger.debug("Password has been reset successfully for User : "+user.getEmail());
				return Constants.REDIRECT_PROFILE;
			} else {
				model.addAttribute(Constants.ERRORMESSAGE, Constants.INVALID_PASSWORD_LINK);
				logger.debug("Could not reset Password  for User : "+resetPasswordForm.getEmail());
				return Constants.REDIRECT_RESETPASSWORD;
			}
		}

	}

	// Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView(Constants.REDIRECT_LOGIN);
	}
}
