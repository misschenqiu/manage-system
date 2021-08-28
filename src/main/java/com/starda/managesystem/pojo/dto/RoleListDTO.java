package com.starda.managesystem.pojo.dto;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto
 * @ClassName: RoleListDTO
 * @Author: chenqiu
 * @Description: 角色实例
 * @Date: 2021/8/28 17:09
 * @Version: 1.0
 */

@Data
public class RoleListDTO {

    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 所属地区
     */
    private String address;

    /**
     * 地址编码
     */
    private String addressCode;

    /**
     * 账号id
     */
    private Integer accountId;

}
