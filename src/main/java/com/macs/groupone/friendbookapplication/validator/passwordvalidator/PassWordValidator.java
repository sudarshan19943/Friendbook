package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class PassWordValidator {
	
	PassWordValidator()
	{
		
	}
	
	String responseLabel="";
	
	private void setText(String errorStr)
	{
		this.responseLabel=errorStr;
	}
	
	public boolean validatePassword(String newPassword, String confirmPassword) {
	    if (StringUtils.isNullOrEmpty(newPassword)
	            || StringUtils.isNullOrEmpty(confirmPassword)) {
	        setText("");
	        return false;
	    }

	    if (!newPassword.equals(confirmPassword)) {
	        setText("Passwords must match.");
	        return false;
	    }

	    PasswordRule lengthRule = new LengthRule();
	    if (!lengthRule.isSatisfied(newPassword)) {
	        setText("Passwords must be between 8 and 50 characters long.");
	        return false;
	    }

	    PasswordRule[] additionalRules = {
	            new HasDigitRule(),
	            new HasLowerCaseRule(),
	            new HasNonAlphanumericRule(),
	            new HasUpperCaseRule(),
	    };

	    long satisfiedRulesCount =
	            Stream.of(additionalRules)
	                    .filter(r -> r.isSatisfied(newPassword))
	                    .count();

	    if (satisfiedRulesCount < 3) {
	        setText("Password does not follow instructions above.");
	        return false;
	    }

	    setText("Password set successfully.");
	    return true;
	}

}
