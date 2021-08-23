package com.starda.managesystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.starda.managesystem.common.AESEncryptHandler;
import com.starda.managesystem.pojo.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto
 * @ClassName: AccountInfo
 * @Author: chenqiu
 * @Description: 账号 信息
 * @Date: 2021/8/21 15:43
 * @Version: 1.0
 */

@Data
public class AccountInfoDTO implements Serializable {
    private static final long serializableUUid = 1L;

    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
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
     * 所属地编码
     */
    private String address_code;

    /**
     * 员工共id
     */
    private Integer staffId;

    /**
     * 员工名字
     */
    private String staffName;

    /**
     * 员工头像
     */
    private String staffImg;

    /**
     * 角色信息
     */
    List<SysRole> roleList;

}
