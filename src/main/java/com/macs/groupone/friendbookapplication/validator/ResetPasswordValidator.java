package com.macs.groupone.friendbookapplication.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.service.UserService;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.EmailValidator;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.PassWordValidator;

@Component
public class ResetPasswordValidator implements Validator {
	
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        
        //need to validate for password Policy
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty");
        
       if (!user.getPassword().equals(user.getPasswordConfirm())){
           errors.rejectValue("password",ValidationCode.PASSWORD_DOES_NOT_MATCH.getPropertyName() );
           return ;
       }
       if (PassWordValidator.validatePasswordPolicy(user.getPassword())!=null){
           errors.rejectValue("password",ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName() );
       }
    }
}
