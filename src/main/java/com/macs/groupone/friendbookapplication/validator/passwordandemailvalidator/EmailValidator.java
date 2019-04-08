package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

public class EmailValidator {

	private EmailValidator() {

	}

	public static boolean isValidEmailAddress(String aEmailAddress) {
		if (aEmailAddress == null) {
			return false;
		}

		boolean result = true;
		if (!hasNameAndDomain(aEmailAddress)) {
			result = false;
		}
		return result;
	}

	private static boolean textHasContent(String text) {
		return (text != null) && (text.trim().length() > 0);
	}

	private static boolean textHasContentAndPersiodSymbol(String text) {
		if ((text != null) && text.trim().contains(".") && (text.trim().length() > 0)) {
			return true;
		} else {
			return false;
		}

	}

	private static boolean hasNameAndDomain(String aEmailAddress) {
		String[] tokens = aEmailAddress.split("@");
		return tokens.length == 2 && textHasContent(tokens[0]) && textHasContentAndPersiodSymbol(tokens[1]);

	}
}
