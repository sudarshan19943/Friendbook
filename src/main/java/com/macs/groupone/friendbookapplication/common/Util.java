package com.macs.groupone.friendbookapplication.common;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Logger;


public abstract class Util {
	private static final Logger log = Logger.getLogger(Util.class.getSimpleName());

	public static String encrypPasswordtSHY2(String password) {
		MessageDigest messageDigest;
		StringBuffer hexString = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			byte byteData[] = messageDigest.digest();

			hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	

}
