package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class HasLowerCaseRule implements PasswordRule{
	
	@Override
    public boolean isSatisfied(String email)  {
        if(email == null) throw new IllegalArgumentException("email must contain @ symbol");

        return false;
    }

}
