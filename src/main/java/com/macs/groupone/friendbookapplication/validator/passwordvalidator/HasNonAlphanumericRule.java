package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class HasNonAlphanumericRule implements PasswordRule {
    @Override
    public boolean isSatisfied(String password) {
        if(password == null) throw new IllegalArgumentException("password cannot be null.");

        for(int i = 0; i < password.length(); i++) {
            if(!Character.isLetterOrDigit(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
