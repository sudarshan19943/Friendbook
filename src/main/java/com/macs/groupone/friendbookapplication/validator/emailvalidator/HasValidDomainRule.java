package com.macs.groupone.friendbookapplication.validator.emailvalidator;
//https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
public class HasValidDomainRule implements EmailRule{
	
	@Override
    public boolean isSatisfied(String password)  {
        if(password == null) throw new IllegalArgumentException("password cannot be null.");

        for(int i = 0; i < password.length(); i++) {
            if(Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }

}
