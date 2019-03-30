package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidator {

	public static boolean isValidEmailAddress(String aEmailAddress) {
		if (aEmailAddress == null)
			return false;
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(aEmailAddress);
			if (!hasNameAndDomain(aEmailAddress)) {
				result = false;
			}
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public static boolean textHasContent(String aText) {
		return (aText != null) && (aText.trim().length() > 0);
	}

	private static boolean hasNameAndDomain(String aEmailAddress) {
		String[] tokens = aEmailAddress.split("@");
		return tokens.length == 2 && textHasContent(tokens[0]) && textHasContent(tokens[1]);

	}
}
