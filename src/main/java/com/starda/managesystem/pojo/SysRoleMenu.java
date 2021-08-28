package com.starda.managesystem.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * sys_role_menu
 * @author 
 */
@Data
public class SysRoleMenu implements Serializable {
    private Integer id;

    /**
     * 角色id
     */
    private Integer role_id;

    /**
     * 权限id
     */
    private Integer menu_id;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 是否可用,0:不可用，1：可用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}