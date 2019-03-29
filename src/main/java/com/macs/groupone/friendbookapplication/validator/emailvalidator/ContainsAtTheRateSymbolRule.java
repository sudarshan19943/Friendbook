package com.macs.groupone.friendbookapplication.validator.emailvalidator;

public class ContainsAtTheRateSymbolRule implements EmailRule{
	
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
