package com.starda.managesystem.pojo.po.business;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.business
 * @ClassName: ConfirmTaskPO
 * @Author: chenqiu
 * @Description: 确认任务信息
 * @Date: 2021/9/4 13:42
 * @Version: 1.0
 */

@Data
public class ConfirmTaskPO {

    /**
     * 业务id
     */
    @NotNull(message = "id不能为空")
    private Integer businessId;

    /**
     * 任务任务类型
     */
    private Integer type;

}
