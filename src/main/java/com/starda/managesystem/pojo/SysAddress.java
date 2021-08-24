package com.starda.managesystem.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys_address
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysAddress implements Serializable {
    private Integer id;

    /**
     * 父级id
     */
    private Integer pId;

    /**
     * 菜单名称
     */
    private String addressName;

    /**
     * 路径id
     */
    private Integer menuId;

    /**
     * 前端 地址
     */
    private String addressUrl;

    /**
     * 是否有子级  0.否 1.是
     */
    private Integer children;

    private static final long serialVersionUID = 1L;
}