package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class ContainsLengthRule implements PasswordRule {
    @Override
    public boolean isCriteriaSatisfied(String password) {
        if(password == null) throw new IllegalArgumentException("password cannot be null.");
        return password.length() >= 8 && password.length() <= 50;
    }
}
