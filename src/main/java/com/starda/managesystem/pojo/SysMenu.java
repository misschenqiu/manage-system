package com.starda.managesystem.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys_menu
 * @author 
 */
@Data
public class SysMenu implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
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
    private Integer p_id;

    /**
     * 前端路径
     */
    private String address_url;

    /**
     * 描述
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}