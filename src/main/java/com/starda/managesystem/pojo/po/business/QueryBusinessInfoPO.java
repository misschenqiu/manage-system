package com.starda.managesystem.pojo.po.business;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.business
 * @ClassName: QueryBusinessInfoPO
 * @Author: chenqiu
 * @Description: 查询业务信息
 * @Date: 2021/9/4 13:40
 * @Version: 1.0
 */

@Data
public class QueryBusinessInfoPO {

    /**
     * 当前页
     */
    @NotNull(message = "请填写页码")
    private Integer currentPage;

    /**
     * 显示条数
     */
    @NotNull(message = "请填写显示条数")
    private Integer pageSize;

    /**
     * 创建人
     */
    private String userName;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 是否结束
     */
    private Integer businessSucess;

    /**
     * 是否回款
     */
    private Integer collectMoney;

}
