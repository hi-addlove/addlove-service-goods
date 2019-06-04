package com.addlove.service.goods.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * HMAC SHA256 加密工具类
 * @author lw
 *
 */
public class SHA256EncryptUtil {

    private static final String CONSTANT = "HmacSHA256";

    /**
     * 加密
     *
     * @param secretKey
     *            秘钥
     * @param content
     *            待加密的内容
     * @return 密文
     */
    public static String encrypt(String secretKey, String content) {
        if (content == null || secretKey == null) {
            return "";
        }

        try {
            Mac mac = Mac.getInstance(CONSTANT);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), CONSTANT);
            mac.init(secretKeySpec);
            // 返回16进制编码的字符串
            return Base64.encodeBase64String(mac.doFinal(content.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (InvalidKeyException e) {
            return "";
        }
    }
}
