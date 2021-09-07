package com.starda.managesystem.pojo.po.app;

import lombok.Data;

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
    private Integer taskType;

    /**
     * 任务名称
     */
    private String taskName;

}
