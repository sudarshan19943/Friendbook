package com.macs.groupone.friendbookapplication.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryptionService {

	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int DERIVEDKEYLENGTH = 160;
	public static final int ITERATIONS = 20000;

	public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}

	public byte[] getEncryptedPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DERIVEDKEYLENGTH);
		SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
		return f.generateSecret(spec).getEncoded();
	}

	public byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}
}
