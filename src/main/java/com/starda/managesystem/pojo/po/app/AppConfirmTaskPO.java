package com.starda.managesystem.pojo.po.app;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.app
 * @ClassName: ConfirmTaskPO
 * @Author: chenqiu
 * @Description: 确认信息
 * @Date: 2021/9/7 9:11
 * @Version: 1.0
 */

@Data
public class AppConfirmTaskPO {

    /**
     * 确认属性 3.确认 4.退回
     */
    @NotNull(message = "确认信息不能为空")
    private Integer confirmType;

    /**
     * 描述
     */
    private String remark;

    /**
     * 业务图片
     */
    private String taskImg;

    /**
     * 详情id
     */
    @NotNull(message = "任务id不能为空！")
    private Integer businessId;

}
