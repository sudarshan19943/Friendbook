package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class LengthRule implements PasswordRule {
    @Override
    public boolean isSatisfied(String password) {
        if(password == null) throw new IllegalArgumentException("password cannot be null.");
        return password.length() >= 8 && password.length() <= 50;
    }
}
