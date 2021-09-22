package com.starda.managesystem.util.getui;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util.getui
 * @ClassName: Sha256
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/21 23:30
 * @Version: 1.0
 */
public class Sha256 {

    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String sha256(String s) {
        try {
            return hex(MessageDigest.getInstance("SHA-256").digest(s.getBytes(Charset.forName("UTF-8"))));
        } catch (NoSuchAlgorithmException var2) {
            throw new AssertionError(var2);
        }
    }

    public static String hex(byte[] data) {
        char[] result = new char[data.length * 2];
        int c = 0;
        byte[] var3 = data;
        int var4 = data.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            result[c++] = HEX_DIGITS[b >> 4 & 15];
            result[c++] = HEX_DIGITS[b & 15];
        }

        return new String(result);
    }

}
