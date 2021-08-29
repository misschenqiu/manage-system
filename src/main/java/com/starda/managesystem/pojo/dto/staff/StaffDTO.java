package com.starda.managesystem.pojo.dto.staff;

import com.baomidou.mybatisplus.annotation.TableField;
import com.starda.managesystem.common.AESEncryptHandler;
import com.starda.managesystem.pojo.enums.UserTypeEnums;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto.staff
 * @ClassName: StaffDTO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/29 16:17
 * @Version: 1.0
 */

@Data
public class StaffDTO {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private UserTypeEnums sex;

    /**
     * 身高
     */
    private Double userHeight;

    /**
     * 电话号码
     */
    @TableField(typeHandler = AESEncryptHandler.class)
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否可用,0:不可用，1：可用
     */
    private Integer status;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 账号id
     */
    private Integer userId;

    /**
     * 职位
     */
    private String position;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建账号
     */
    private Integer createAccountId;

    /**
     * 修改时间
     */
    private Date updateTime;

}
