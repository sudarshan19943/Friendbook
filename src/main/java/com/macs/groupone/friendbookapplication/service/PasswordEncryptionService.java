package com.macs.groupone.friendbookapplication.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptionService implements IService{

<<<<<<< HEAD
	public static final String UTF8 = "UTF-8";
	public static final String SHA1 = "SHA-1";
	public static final String AES = "AES";
	public static final String AES_ECB_PKCS5PADDING = "AES/ECB/PKCS5PADDING";
=======
	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int DERIVEDKEYLENGTH = 160;
	public static final int ITERATIONS = 20000;
>>>>>>> dc3672108c40cee56e8314ee40fcd272868d1357

	
	public static String encrypt(String toEncrypt, String secret)
	{
		try
		{
			SecretKeySpec secretKey=generateSecretKey(secret);
			Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes(UTF8)));
		}
		catch (Exception e)
		{
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
<<<<<<< HEAD
	public  static String decrypt(String toDecrypt, String secret)
	{
		try
		{
			SecretKeySpec secretKey=generateSecretKey(secret);
			Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5PADDING);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
		}
		catch (Exception e)
		{
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
=======

	public byte[] getEncryptedPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DERIVEDKEYLENGTH);
		SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
		return f.generateSecret(spec).getEncoded();
>>>>>>> dc3672108c40cee56e8314ee40fcd272868d1357
	}
	
	private static  SecretKeySpec generateSecretKey(String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		byte[] key = secret.getBytes(UTF8);
		MessageDigest sha = MessageDigest.getInstance(SHA1);
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		SecretKeySpec secretKey = new SecretKeySpec(key,AES);
		return secretKey;
	}
}
