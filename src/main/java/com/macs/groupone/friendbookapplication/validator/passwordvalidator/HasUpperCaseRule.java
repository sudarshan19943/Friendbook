package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class HasUpperCaseRule implements PasswordRule {
    @Override
    public boolean isSatisfied(String password) {
        if(password == null) throw new IllegalArgumentException("password cannot be null.");

        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
