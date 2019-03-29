package com.macs.groupone.friendbookapplication.validator.passwordvalidator;

public class StringUtils {
	
	 public static boolean isNullOrEmpty(String string) {
	        return string == null || string.length() == 0;
	    }

	    public static boolean isNullOrWhitespace(String string) {
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

}
