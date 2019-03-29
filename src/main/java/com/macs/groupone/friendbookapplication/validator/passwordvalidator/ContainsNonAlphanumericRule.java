package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class ContainsNonAlphanumericRule implements PasswordRule {
    @Override
    public boolean isCriteriaSatisfied(String password) {
        for(int i = 0; i < password.length(); i++) {
            if(!Character.isLetterOrDigit(password.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
