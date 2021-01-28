package com.jgma.xcode.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
	private static final String CRYPT_KEY = "v3VC7yulq6IL5KgjukqZrQ1b";
	private static final String CRYPT_ALGORITHM = "DESede";

    public static String decrypt(String value, String password) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decodedByte = Base64.decodeBase64(value);
            byte[] decryptedByte = cipher.doFinal(decodedByte);            
            return new String(decryptedByte);
        } catch(Exception e) {
            return null;
        }
    }
    
    public static String encrypt(String value, String password) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedByte = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encryptedByte).trim();
        } catch(Exception e) {
            return null;
        }
    }
    
    public static String encrypt(String value) {
    	return encrypt(value, CRYPT_KEY);
    }
    public static String decrypt(String value) {
    	return decrypt(value, CRYPT_KEY);
    }

    public static void main(String[] args) {
        String a = encrypt("你好啊");
        String decrypt = decrypt(a);

        System.out.println(a);
        System.out.println(decrypt);
    }

}
