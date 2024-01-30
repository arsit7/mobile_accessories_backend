package com.mobile.accessorie.common.util;

import java.security.NoSuchAlgorithmException;


import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptionUtil {

	public static String generateSalt() {
		SecureRandom RAND = new SecureRandom();
		byte[] bytes = new byte[16];
		RAND.nextBytes(bytes);
		String salt = Base64.getEncoder().encodeToString(bytes);
		return salt;
	}

	public static String hashPassword(String password, String salt) {
		int iterations = 10000;
		int keyLength = 256;
		char[] chars = password.toCharArray();
		byte[] bytes = salt.getBytes();
		PBEKeySpec spec = new PBEKeySpec(chars, bytes, iterations, keyLength);
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			byte[] hashedBytes = keyFactory.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(hashedBytes);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
	}
}
