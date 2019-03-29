package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class PassWordValidator {
	
	PassWordValidator()
	{
		
	}
<<<<<<< HEAD
	
	String responseLabel="";
	
=======
	String responseLabel="";
>>>>>>> 1dd588d12d2c778694225ecb646ed93fbdc2b10a
	private void setText(String errorStr)
	{
		this.responseLabel=errorStr;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 1dd588d12d2c778694225ecb646ed93fbdc2b10a
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
<<<<<<< HEAD

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

=======
	    PasswordRule lengthRule = new ContainsLengthRule();
	    if (!lengthRule.isCriteriaSatisfied(newPassword)) {
	        setText("Passwords must be between 8 and 50 characters long.");
	        return false;
	    }
	    PasswordRule[] additionalRules = {
	            new ContainsDigitRule(),
	            new ContainesLowerCaseRule(),
	            new ContainsNonAlphanumericRule(),
	            new ContainsUpperCaseRule(),
	    };
	    long satisfiedRulesCount =
	            Stream.of(additionalRules)
	                    .filter(r -> r.isCriteriaSatisfied(newPassword))
	                    .count();
>>>>>>> 1dd588d12d2c778694225ecb646ed93fbdc2b10a
	    if (satisfiedRulesCount < 3) {
	        setText("Password does not follow instructions above.");
	        return false;
	    }
<<<<<<< HEAD

=======
>>>>>>> 1dd588d12d2c778694225ecb646ed93fbdc2b10a
	    setText("Password set successfully.");
	    return true;
	}

}
