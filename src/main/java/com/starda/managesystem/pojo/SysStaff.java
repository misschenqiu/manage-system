package com.starda.managesystem.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_staff
 * @author 
 */
@Data
public class SysStaff implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 身高
     */
    private Double user_height;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date create_time;

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
    private String head_img;

    /**
     * 账号id
     */
    private Integer user_id;

    /**
     * 职位
     */
    private String position;

    /**
     * 描述
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}