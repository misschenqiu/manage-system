package com.starda.managesystem.pojo.po.app;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.app
 * @ClassName: AppTaskQueryPO
 * @Author: chenqiu
 * @Description: app任务查看信息列表
 * @Date: 2021/9/7 9:07
 * @Version: 1.0
 */
@Data
public class AppTaskQueryPO {

    /**
     * 查看类型 1.待完成 2.已完成
     */
    @NotNull(message = "查看类型不能为空 1.待完成 2.已完成")
    private Integer taskType;

    /**
     * 任务名称
     */
    private String taskName;

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
