package com.szty.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.UnknownAlgorithmException;

public class EncryptUtil {
	
	private static final int DEFAULT_ITERATIONS = 1;
	
	private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static String encrypt(String encryptStr) {
		return encrypt(encryptStr, null, 1, null);
	}
	
	public static String encrypt(String encryptStr, String salt, int hashIterations, String algorithmName){
		if (StringUtils.isEmpty(encryptStr)) {
			return null;
		}
		if (StringUtils.isEmpty(salt)) {
			salt = null;
		}
		if (StringUtils.isEmpty(algorithmName)) {
			algorithmName = "MD5";
		}
		
		byte[] sourceBytes = encryptStr.getBytes();
		byte[] saltBytes = salt == null ? null : salt.getBytes();
		byte[] resultBytes;
		try {
			resultBytes = hash(sourceBytes, saltBytes, hashIterations, algorithmName);
			return bytesToHexString(resultBytes);
		} catch (UnknownAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
     * Hashes the specified byte array using the given {@code salt} for the specified number of iterations.
     *
     * @param bytes          the bytes to hash
     * @param salt           the salt to use for the initial hash
     * @param hashIterations the number of times the the {@code bytes} will be hashed (for attack resiliency).
     * @return the hashed bytes.
     * @throws UnknownAlgorithmException if the {@link #getAlgorithmName() algorithmName} is not available.
     */
    protected static byte[] hash(byte[] bytes, byte[] salt, int hashIterations, String algorithmName) throws UnknownAlgorithmException {
        MessageDigest digest = getDigest(algorithmName);
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }
        byte[] hashed = digest.digest(bytes);
        int iterations = hashIterations - DEFAULT_ITERATIONS; //already hashed once above
        //iterate remaining number:
        for (int i = 0; i < iterations; i++) {
            digest.reset();
            hashed = digest.digest(hashed);
        }
        return hashed;
    }
	
	/**
     * Returns the JDK MessageDigest instance to use for executing the hash.
     *
     * @param algorithmName the algorithm to use for the hash, provided by subclasses.
     * @return the MessageDigest object for the specified {@code algorithm}.
     * @throws UnknownAlgorithmException if the specified algorithm name is not available.
     */
    protected static MessageDigest getDigest(String algorithmName) throws UnknownAlgorithmException {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            String msg = "No native '" + algorithmName + "' MessageDigest instance available on the current JVM.";
            throw new UnknownAlgorithmException(msg, e);
        }
    }

    protected static String bytesToHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

    protected byte[] hexStringToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
}
