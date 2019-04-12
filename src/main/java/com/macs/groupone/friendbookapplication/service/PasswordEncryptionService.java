package com.macs.groupone.friendbookapplication.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

//Referred from Rob's Lecture and Tutorials - Refactoring

public class PasswordEncryptionService implements IService {
	
	private PasswordEncryptionService() {
	}
	
	final static Logger logger = Logger.getLogger(PasswordEncryptionService.class);


	public static final String UTF8 = "UTF-8";
	public static final String SHA1 = "SHA-1";
	public static final String AES = "AES";
	public static final String AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5PADDING";

	public static String encrypt(String toEncrypt, String secret) {
		try {
			SecretKeySpec secretKey = generateSecretKey(secret);
			Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes(UTF8)));
		} catch (Exception e) {
			logger.error("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String toDecrypt, String secret) {
		try {
			SecretKeySpec secretKey = generateSecretKey(secret);
			Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
		} catch (Exception e) {
			logger.error("Error while decrypting: " + e.toString());
		}
		return null;
	}

	private static SecretKeySpec generateSecretKey(String secret)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] key = secret.getBytes(UTF8);
		MessageDigest sha = MessageDigest.getInstance(SHA1);
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		SecretKeySpec secretKey = new SecretKeySpec(key, AES);
		return secretKey;
	}
}
