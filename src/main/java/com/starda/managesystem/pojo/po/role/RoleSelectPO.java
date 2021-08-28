package com.starda.managesystem.pojo.po.role;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.role
 * @ClassName: RoleSelectPO
 * @Author: chenqiu
 * @Description: 角色查询
 * @Date: 2021/8/27 0:37
 * @Version: 1.0
 */

@Data
public class RoleSelectPO {

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 地区编码
     */
    private String addressCode;

    /**
     * 是否是最大管理员
     */
    private Boolean maxManger = false;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 显示条数
     */
    private Integer pageSize;

}
