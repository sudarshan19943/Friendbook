package com.macs.groupone.friendbookapplication.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.ServiceFactory;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.EmailValidator;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.StringUtils;

//References -
//https://www.journaldev.com/2668/spring-validation-example-mvc-validator
//https://dzone.com/tutorials/java/spring/spring-form-validation-1.html

public class LoginValidator implements Validator {

	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String NOTEMPTY = "NotEmpty";

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		User user = (User) o;

		UserService userService = (UserService) ServiceFactory.getInstance().getUserService();

		if (StringUtils.isNullOrEmpty(user.getEmail())) {
			errors.rejectValue(EMAIL, ValidationCode.NOTEMPTY.getPropertyName());
			return;
		}

		if (StringUtils.isNullOrEmpty(user.getPassword())) {
			errors.rejectValue(PASSWORD, ValidationCode.NOTEMPTY.getPropertyName());
			return;
		}

		if (!EmailValidator.isValidEmailAddress(user.getEmail())) {
			errors.rejectValue(EMAIL, ValidationCode.EMIAL_NOT_VALID.getPropertyName());
			return;
		}
		if (userService.getUserByEmail(user.getEmail()) == null) {
			errors.rejectValue(EMAIL, ValidationCode.EMAIL_NOT_FOUND.getPropertyName());
			return;
		}

	}
}
