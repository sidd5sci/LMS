package com.frapwise.utils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;


public class AES_Cipher {

	private static final String ALGORITHM = "AES";

    private static final byte[] SALT = "tHeApAcHe6410111".getBytes();// THE KEY MUST BE SAME
    private static final String X = AES_Cipher.class.getSimpleName();
       
    
    
    public static String getEncrypted(String plainText) {

        if (plainText == null) {
            return null;
        }

        Key salt = getSalt();

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encodedValue);
//            return Base64.encode(encodedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Failed to encrypt data");
    }

    public static String getDecrypted(String encodedText) {

        if (encodedText == null) {
            return null;
        }

        Key salt = getSalt();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, salt);
            byte[] decodedValue = Base64.getDecoder().decode(encodedText);
//            byte[] decodedValue = Base64.decode(encodedText);
            byte[] decValue = cipher.doFinal(decodedValue);
            return new String(decValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }
}
