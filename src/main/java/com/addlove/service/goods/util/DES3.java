package com.addlove.service.goods.util;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Karas
 * 3DES 加解密算法，此算法用于与MSC系统算法对接，并非标准算法，请谨慎修改
 * 算法在正常算法基础上进行了接口适应的特定修改，请不要在其它地方使用此类
 */

public class DES3 {
    private final static String Algorithm = "DESede";
    private final static char[] base = { '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z' };

    /**
     * 加密
     * @param message 明文
     * @param strKey  密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String message, String strKey) throws Exception {
        message = message + "        ";
        byte[] bMsg = message.getBytes("gbk");
        int l = (bMsg.length / 16 + 1) * 16;
        byte[] btMsg = Arrays.copyOf(bMsg, l);
        
        byte[] digestOfPassword = strKey.getBytes();
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        // 定义 加密算法,可用 DES,DESede,Blowfish
        SecretKey key = new SecretKeySpec(keyBytes, Algorithm);
        // 加密
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, key);
        String rtn = byte2hex(c1.doFinal(btMsg));
        return rtn.substring(0, (bMsg.length / 8 + 1) * 16);
    }

    /**
     * 校正算法。。。
     * @param length
     * @param strKey
     * @return
     * @throws Exception
     */
    public static String getAdd(int length, String strKey) throws Exception {
        byte[] btMsg = new byte[length / 2];
        
        byte[] digestOfPassword = strKey.getBytes("utf-8");
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        SecretKey key = new SecretKeySpec(keyBytes, Algorithm);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, key);
        
        String rtn = byte2hex(c1.doFinal(btMsg));
        
        return rtn.substring(length);
    }

    /**
     * 解密算法
     * @param message 密文
     * @param strKey  密钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String message, String strKey) throws Exception {
        message = message + getAdd(message.length(), strKey);
        byte[] digestOfPassword = strKey.getBytes();
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
        keyBytes[k++] = keyBytes[j++];
        }
        
        SecretKey key = new SecretKeySpec(keyBytes, Algorithm);
        Cipher decipher = Cipher.getInstance(Algorithm);
        decipher.init(Cipher.DECRYPT_MODE, key);
        
        byte[] plainText = decipher.doFinal(hex2byte(message));
        
        return new String(plainText, "gbk");
    }

    /**
     * 产生一个新密钥
     * @param len  密钥长度
     * @return
     */
    public static String newKey(int len) {
        java.util.Random rd = new java.util.Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
        sb.append(base[Math.abs(rd.nextInt()) % 62]);
        }
        return sb.toString();
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
                else
                    hs = hs + stmp;
            }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(String s) {
        int m = 0, n = 0;
        int l = s.length() / 2;
        byte[] b = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            b[i] = uniteBytes(s.substring(i * 2, m), s.substring(m, n));
        }
        return b;
    }

    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

   /* public static void main(String[] args) {
    
        String src = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><InputParameter><TranType>1</TranType><SaleNo>P34424242</SaleNo><UserCode>6688</UserCode><CshName></CshName><CshCode></CshCode><Password>123456</Password><RandomNo>-1812444856</RandomNo><SMSCode></SMSCode><WorkGuid>{4EBC6281-8455-4CCB-8F97-767CFF18B414}</WorkGuid><CardNo>2010091500000002</CardNo><AccType>1</AccType><XsDate>2010-09-19 09:39:47</XsDate><ZfTotal>1.1</ZfTotal><OrgCode>3173</OrgCode><CardNoType>1</CardNoType><OldSaleNo></OldSaleNo><SerialNo></SerialNo><POSNo></POSNo><Remark>测试</Remark></InputParameter>";
        String key = "3160857325476982";
        String rst = "857AE6F7169D84019AD836D5C8E3C18A3C9662DE290024BE64C28277AD9BAAF7450B881B4D350460F8EEAC708EAA91143EC80B10E2CAFB5C52705EB4EF47CC4994F49ECFC2B2A0E3EEB31648AEF992E20ECA3B471B84CB9D5BB4007E18AF4AEA84FAC5150F10EB4D01D0835C759153D8F7A1A0C11ABE5BB959D4505320795737FB1AE4CFAB0090469B985739CE8DC7A2A65E7C10FBA2C42394C2D4A5F79169B07729DFA3AC2818F168A630530864C45949C7538306A071C26E5F66AD2CF11160073DDD1A7D8CE9FA45745B4E787A50AE0A3926A69336AAC4DD37E026BA18EBE305D9BA6EF7398D73E76AD6675991382AE46BFCADD2776C2847E46BA8AA7EE5BE6040C4888988FBB861563D45FB4AE38A8B7B01DD2888DBBD3587CBDB9FA8B8154012F34C76502A01B3C96837D5BF7B737E773ED880B56F5AC48FB35F64A6C05E9172837253D8F079AC02E36C15E16B84584FA2668ABAC593E482A52626FA61E5AD3C09654A3C9A5DCA0722000E8180969E16628A30C904F705FCCB9A28435BE1024C61714CC06736124E421654C4CBACF668F169EAE054347FD6A7E32014A6BAAB9D4D3BFE1EB133030A049215AAECA527E05D8382B4FE345234B312272AD0F0F16AAC172174156281EB0D236169FDE7D1B3079BA02350E17A2ACA7AF7991EEDECEFF37BA164DC0159663720DA11263F9EE7A635229C12762A48CB4DC1107D49A689CF81EA1D49A9B63D6101AA8D27E340B11EF67BF019A11A9CBB286E1493F7B45395A0D8F717AEA82D7240AAFE4A3D59698C72F281F0F3FF743F176C914773D3F681D8FADD9060736CB25207BD5B9248D26D3499DF3DB6D83069029B0EA8A21DCF5556A97F5FB98ABFC9E081826303EF968C7B26D770258586B84C19C7EE0A";
        
        try {
            // rst = DES3.encrypt(src, key);
            src = DES3.decrypt(rst, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println(src);
        System.out.println(rst);
    }*/
}