package com.starda.managesystem.pojo.vo.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.role
 * @ClassName: RoleListVO
 * @Author: chenqiu
 * @Description: 角色实例列表
 * @Date: 2021/8/27 0:40
 * @Version: 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleListVO {

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

}
