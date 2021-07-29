package com.starda.managesystem.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: AES
 * @Author: chenqiu
 * @Description: 加密工具
 * @Date: 2021/7/29 15:24
 * @Version: 1.0
 */

@Slf4j
public class AES {

    /**
     * 密钥
     */
    public static String key = "AD42F6697B035B7580E4FEF93BE20BAD";
    private static String charset = "utf-8";
    /**
     * 偏移量
     */
    private static int offset = 16;
    private static String transformation = "AES/CBC/PKCS5Padding";
    private static String algorithm = "AES";

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        return encrypt(content, key);
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        return decrypt(content, key);
    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param key     加密密码
     * @return
     */
    public static String encrypt(String content, String key) {
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, offset);
            Cipher cipher = Cipher.getInstance(transformation);
            byte[] byteContent = content.getBytes(charset);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);
            byte[] result = cipher.doFinal(byteContent);
            // 加密
            return new Base64().encodeToString(result);
        } catch (Exception e) {
            log.error("加密失败" + e);
        }
        return null;
    }

    /**
     * AES（256）解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return 解密之后
     * @throws Exception
     */
    public static String decrypt(String content, String key) {
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), algorithm);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(), 0, offset);
            Cipher cipher = Cipher.getInstance(transformation);
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, skey, iv);
            byte[] result = cipher.doFinal(new Base64().decode(content));
            // 解密
            return new String(result);
        } catch (Exception e) {
            log.error("AES（256）解密" + e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String s = "jdbc:mysql://121.196.110.80:3306/manage_system?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2b8";
        String username = "root";
        String password = "Qiudaye90900..";
        // 加密
//        System.out.println("加密前：" + s);
//        String encryptResultStr = encrypt(s);
//        System.out.println("加密后：" + encryptResultStr);
//        // 解密
//        System.out.println("解密后：" + decrypt(encryptResultStr));
//        System.out.println(com.baomidou.mybatisplus.core.toolkit.AES.generateRandomKey());
        String key = "408710481a57bb3f";
        System.out.println("url:->"+com.baomidou.mybatisplus.core.toolkit.AES.encrypt("jdbc:mysql://121.196.110.80:3306/manage_system?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2b8" , "408710481a57bb3f"));
        System.out.println(com.baomidou.mybatisplus.core.toolkit.AES.encrypt(username , "408710481a57bb3f"));
        System.out.println(com.baomidou.mybatisplus.core.toolkit.AES.encrypt(password , "408710481a57bb3f"));
    }
}