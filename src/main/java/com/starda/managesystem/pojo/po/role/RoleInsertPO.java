package com.starda.managesystem.pojo.po.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.role
 * @ClassName: RoleInsertPO
 * @Author: chenqiu
 * @Description: 添加角色
 * @Date: 2021/8/27 0:44
 * @Version: 1.0
 */

@Data
public class RoleInsertPO {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    /**
     * 角色编码
     */
    private String addressName;

    /**
     * 地区编码
     */
    private String addressCode;

    /**
     * 说明
     */
    private String remark;

    /**
     * 权限id集合
     */
    private List<Integer> menuIds;

}
