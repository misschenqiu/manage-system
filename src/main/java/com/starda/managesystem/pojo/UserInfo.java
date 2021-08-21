package com.starda.managesystem.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.starda.managesystem.common.AESEncryptHandler;
import com.starda.managesystem.pojo.enums.UserTypeEnums;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * user_info
 * @author 
 */
@Data
@TableName(autoResultMap = true)
@ToString
public class UserInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @TableField(typeHandler = AESEncryptHandler.class)
    private String password;

    /**
     * 性别
     */
    private UserTypeEnums sex;

    private static final long serialVersionUID = 1L;
}