package com.starda.managesystem.pojo;

import java.io.Serializable;

import com.starda.managesystem.pojo.enums.UserTypeEnums;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private UserTypeEnums sex;

    private static final long serialVersionUID = 1L;
}