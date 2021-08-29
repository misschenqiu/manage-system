package com.starda.managesystem.common;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.common
 * @ClassName: SecurityPasswordCommon
 * @Author: chenqiu
 * @Description: 加密工具
 * @Date: 2021/8/22 11:41
 * @Version: 1.0
 */
public class SecurityPasswordCommon {

    /**
     * security 加密
     * @param password 密码
     * @return
     */
    public static String password(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /**
     * 获取uuid
     * @return
     */
    public static String getUuid(){
       return UUID.fastUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据 账号生成 密钥
     * @param account
     * @return
     */
    public static String generateKey(String account){
        return SecureUtil.md5(account + new Date());
    }

    /**
     * 判断密码的准确性
     * @param oldPassword 旧密码
     * @param password 输入未加密密码
     * @return
     */
    public static boolean isPassword(String password, String oldPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, oldPassword);
    }

}
