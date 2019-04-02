package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

public class ContainsUpperCaseRule implements PasswordRule {
    @Override
    public boolean isCriteriaSatisfied(String password) {
        if(password == null) throw new IllegalArgumentException("password cannot be null.");

        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) {
            	System.out.println("password criteria satisfied"+password.charAt(i));
                return true;
            }
        }

        return false;
    }
}
