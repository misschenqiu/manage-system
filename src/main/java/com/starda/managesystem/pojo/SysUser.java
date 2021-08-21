package com.starda.managesystem.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.starda.managesystem.common.AESEncryptHandler;
import lombok.Data;

/**
 * sys_user
 * @author 
 */
@Data
public class SysUser implements Serializable {
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @TableField(typeHandler = AESEncryptHandler.class)
    private String password;

    /**
     * 电话号码
     */
    @TableField(typeHandler = AESEncryptHandler.class)
    private String phone;

    /**
     * 账号所属地
     */
    private String address;

    /**
     * 是否可用,0:不可用，1：可用
     */
    private Integer status;

    /**
     * 所属地编码
     */
    private String address_code;

    private static final long serialVersionUID = 1L;
}