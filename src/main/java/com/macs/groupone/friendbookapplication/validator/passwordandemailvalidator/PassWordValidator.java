package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.macs.groupone.friendbookapplication.config.Config;
import com.macs.groupone.friendbookapplication.validator.ValidationCode;


public class PassWordValidator {
	
	
	
	public static String validatePasswordPolicy(String newPassword) {
	    PasswordRule lengthRule = new ContainsLengthRule();
	    if (!lengthRule.isCriteriaSatisfied(newPassword)) {
	    	return ValidationCode.PASSWORD_SIZE_POLICY.getPropertyName();
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
	    if (satisfiedRulesCount < 3) {
	    	return ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName();
	    }
	    return null;
	}

}
