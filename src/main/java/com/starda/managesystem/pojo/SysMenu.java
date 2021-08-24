package com.starda.managesystem.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys_menu
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    /**
     * 父级id
     */
    private Integer pId;

    /**
     * 前端路径
     */
    private String addressUrl;

    private static final long serialVersionUID = 1L;
}