package com.addlove.service.goods.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 * @author lw
 */
public class MD5Util {

    /**
     * 常量
     */
    private static final int MOD = 0xff;

    /**
     * 常量位数
     */
    private static final int LEN = 16;

    /**
     * @param encryptStr
     *            明文
     * @Description:加密-32位小写
     * @return 密文
     */
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & MOD;
                if (val < LEN) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
