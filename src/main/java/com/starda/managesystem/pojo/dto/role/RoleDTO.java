package com.starda.managesystem.pojo.dto.role;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto.role
 * @ClassName: RoleDTO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/29 0:55
 * @Version: 1.0
 */
@Data
public class RoleDTO {

    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

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
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 角色所属地址
     */
    private String address;

    /**
     * 所属地区编码
     */
    private String addressCode;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 创建人id
     */
    private Integer createAccountId;

}
