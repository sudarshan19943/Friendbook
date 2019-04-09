package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import java.util.stream.Stream;
import com.macs.groupone.friendbookapplication.validator.ValidationCode;

public class PasswordValidator {

	private PasswordValidator() {

	}

	public static String validatePasswordPolicy(String newPassword) {
		PasswordRule lengthRule = new ContainsLengthRule();
		if (!lengthRule.isCriteriaSatisfied(newPassword)) {
			return ValidationCode.PASSWORD_SIZE_POLICY.getPropertyName();
		}
		PasswordRule[] additionalRules = { new ContainsDigitRule(), new ContainesLowerCaseRule(),
				new ContainsNonAlphanumericRule(), new ContainsUpperCaseRule(), };
		long satisfiedRulesCount = Stream.of(additionalRules).filter(r -> r.isCriteriaSatisfied(newPassword)).count();
		if (satisfiedRulesCount < 3) {
			return ValidationCode.PASSWORD_POLICY_DOES_NOT_SATISFY.getPropertyName();
		}
		return null;
	}

}
