package com.example.spring.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * AES加解密工具类
 */
public class AES {

    /**
     * AES加密
     *
     * @param key
     * @param iv
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static byte[] encryptAes(byte[] data, byte[] key, byte[] iv)
            throws GeneralSecurityException, IOException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }

    /**
     * AES解密
     *
     * @param key
     * @param iv
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static byte[] decryptAesToByteString(byte[] data, byte[] key, byte[] iv)
            throws GeneralSecurityException, IOException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }
}

