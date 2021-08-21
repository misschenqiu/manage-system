package com.starda.managesystem.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_menu
 * @author 
 */
@Data
public class SysMenu implements Serializable {
    private Integer id;

    /**
     * 菜单名
     */
    private String menu_name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 路劲
     */
    private String url;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date modified_time;

    private static final long serialVersionUID = 1L;
}