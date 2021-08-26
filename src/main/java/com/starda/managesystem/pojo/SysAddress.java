package com.starda.managesystem.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * sys_address
 * @author 
 */
@Data
@NotBlank
@AllArgsConstructor
@NoArgsConstructor
public class SysAddress implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
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

    /**
     * 浏览器地址
     */
    private String route;

    /**
     * 图标
     */
    private String icon;

    /**
     * 前端自用
     */
    private String redirect;

    private static final long serialVersionUID = 1L;
}