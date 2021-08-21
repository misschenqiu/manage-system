package com.starda.managesystem.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_role
 * @author 
 */
@Data
public class SysRole implements Serializable {
    private Integer id;

    /**
     * 角色名
     */
    private String role_name;

    /**
     * 描述
     */
    private String remark;

    /**
     * 是否可用,0:不可用，1：可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date modified_time;

    /**
     * 角色所属地址
     */
    private String address;

    /**
     * 所属地区编码
     */
    private String address_code;

    private static final long serialVersionUID = 1L;
}