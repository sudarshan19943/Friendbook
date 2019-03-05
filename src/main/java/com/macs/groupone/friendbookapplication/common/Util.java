package com.macs.groupone.friendbookapplication.common;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Util {
	
	private static final Logger log = LoggerFactory.getLogger(Util.class);
	
	 static final String MESSAGE_DIGEST="SHA-256";

	public static String encrypPasswordtSHY2(String password) {
		MessageDigest messageDigest;
		StringBuffer hexString = null;
		try {
			messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST);
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
			log.error(e.getMessage());
			
		}
		return hexString.toString();
	}

	

}

