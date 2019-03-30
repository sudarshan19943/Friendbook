package com.macs.groupone.friendbookapplication.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.EmailValidator;

@Component
public class LoginValidator implements Validator {
	
	public static final String EMAIL="email";
	public static final String PASSWORD="password";
	
	
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        
        if (!EmailValidator.isValidEmailAddress(user.getEmail())) {
            errors.rejectValue("email",ValidationCode.EMIAL_NOT_VALID.getPropertyName() );
            return;
        }
       
        if (userService.getUserByEmail(user.getEmail()) == null) {
            errors.rejectValue("email",ValidationCode.EMAIL_NOT_FOUND.getPropertyName() );
            return;
        }
        
        if (null==userService.getUserByEmailPassword(user.getEmail(), user.getPassword())){
            errors.rejectValue("password",ValidationCode.AUTHENTICATION_ERROR.getPropertyName() );
        }
        
    }
}
