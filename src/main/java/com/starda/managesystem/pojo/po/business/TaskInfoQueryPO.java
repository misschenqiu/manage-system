package com.starda.managesystem.pojo.po.business;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.business
 * @ClassName: TaskInfoQueryPO
 * @Author: chenqiu
 * @Description: 查询任务信息
 * @Date: 2021/9/4 13:32
 * @Version: 1.0
 */

@Data
public class TaskInfoQueryPO {

    /**
     * 业务id
     */
    @NotNull(message = "业务id不能为空")
    private Integer businessId;

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

}
