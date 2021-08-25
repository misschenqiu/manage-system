package com.starda.managesystem.pojo.po.address;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.address
 * @ClassName: SysMenuAddressPO
 * @Author: chenqiu
 * @Description: 权限路径
 * @Date: 2021/8/25 13:57
 * @Version: 1.0
 */

@Data
public class SysMenuAddressPO {

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 显示条数
     */
    private Integer pageSize;

    /**
     * 简称
     */
    private String smileName;

}
