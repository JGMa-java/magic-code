package com.jgma.xcode.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * 整个块不进行除法，整个块一起处理以生成密文。
 * 相对而言，AES比DES快得多，并且与DES相比，AES能够在几秒钟内加密大型文件。
 * 由于DES中使用的共享密钥的比特大小较小，因此它被认为不如AES安全。DES被认为更容易受到暴力攻击，而到目前为止，尚未遇到任何严重攻击的AES。
 * 在灵活性的基础上评估算法的实现，并且AES比DES更具灵活性，因为它允许包括128、192、256位在内的各种长度的文本，而DES允许对64位固定文本进行加密。
 * @author jgma
 */
public class AESUtils {

    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    // 获取 cipher
    private static Cipher getCipher(byte[] key, int model) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        return cipher;
    }

    // AES加密
    public static String encrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }

    // AES解密
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Cipher cipher = getCipher(key, Cipher.DECRYPT_MODE);
        return cipher.doFinal(Base64.getDecoder().decode(data));
    }

}
