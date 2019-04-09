package com.macs.groupone.friendbookapplication.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.macs.groupone.friendbookapplication.model.User;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.PasswordValidator;
import com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator.StringUtils;

public class ResetPasswordValidator implements Validator {
	
	
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String NOTEMPTY = "NotEmpty";
	public static final String PASSWORD_CONFIRM = "passwordConfirm";

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        
        if (StringUtils.isNullOrEmpty(user.getPassword())){
            errors.rejectValue(PASSWORD,ValidationCode.NOTEMPTY.getPropertyName() );
            return ;
        }
        
        if (StringUtils.isNullOrEmpty(user.getPasswordConfirm())){
            errors.rejectValue(PASSWORD_CONFIRM,ValidationCode.NOTEMPTY.getPropertyName() );
            return ;
        }
        
       if (!user.getPassword().equals(user.getPasswordConfirm())){
           errors.rejectValue(PASSWORD,ValidationCode.PASSWORD_DOES_NOT_MATCH.getPropertyName() );
           return ;
       }
       if (PasswordValidator.validatePasswordPolicy(user.getPassword())!=null){
           errors.rejectValue(PASSWORD,ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName() );
       }
    }
}
