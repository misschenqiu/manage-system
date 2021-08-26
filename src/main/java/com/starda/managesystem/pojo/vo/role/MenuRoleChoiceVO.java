package com.starda.managesystem.pojo.vo.role;

import lombok.Builder;
import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.role
 * @ClassName: MenuRoleChoiceVO
 * @Author: chenqiu
 * @Description: 角色权限选择 返回
 * @Date: 2021/8/27 0:24
 * @Version: 1.0
 */

@Data
@Builder
public class MenuRoleChoiceVO {

    private Integer id;

    /**
     * 路径名
     */
    private String title;

    /**
     * 简称
     */
    private String smilName;

    /**
     * 描述
     */
    private String remark;

    /**
     * 父级ia
     */
    private Integer pId;

    /**
     * 图标
     */
    private String icon;


}
