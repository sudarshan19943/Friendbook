package com.macs.groupone.friendbookapplication.validator.passwordandemailvalidator;

public class StringUtils {

	private StringUtils() {

	}

	public static boolean isNullOrWhiteSpace(String string) {
		if (string == null) {
			return true;
		}
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isWhitespace(string.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.length() == 0;
	}

}
