package com.starda.managesystem.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto
 * @ClassName: RoleInfoListDTO
 * @Author: chenqiu
 * @Description: 角色信息列表
 * @Date: 2021/8/22 22:45
 * @Version: 1.0
 */

@Data
public class RoleInfoListDTO implements Serializable {

    private Integer id;

    /**
     * 角色名
     */
    private String role_name;

    /**
     * 角色编码
     */
    private String role_code;

    /**
     * 描述
     */
    private String remark;

    /**
     * menu id
     */
    private List<Integer> menuIds;


    private static final long serialVersionUID = 1L;
}
