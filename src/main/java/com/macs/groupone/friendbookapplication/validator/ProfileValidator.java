package com.macs.groupone.friendbookapplication.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.macs.groupone.friendbookapplication.model.User;

@Component
public class ProfileValidator implements Validator {
	
  
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        
       /*
       if (user.getUserImage().getBytes() >  120986) {
           errors.rejectValue("userImage",ValidationCode.PROFILE_MAX__SIZE.getPropertyName() );
           return;
       }*/
      

    }
}
