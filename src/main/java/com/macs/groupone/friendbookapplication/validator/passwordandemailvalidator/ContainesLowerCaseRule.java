package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

public class ContainesLowerCaseRule implements PasswordRule{
	
	 @Override
	    public boolean isCriteriaSatisfied(String password) {
	        for(int i = 0; i < password.length(); i++) {
	            if(Character.isLowerCase(password.charAt(i))) {
	                return true;
	            }
	        }
	        return false;
	    }

}
