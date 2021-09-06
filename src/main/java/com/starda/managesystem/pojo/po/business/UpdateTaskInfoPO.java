package com.starda.managesystem.pojo.po.business;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.business
 * @ClassName: UpdateTaskInfoPO
 * @Author: chenqiu
 * @Description: 任务详情修改
 * @Date: 2021/9/7 0:43
 * @Version: 1.0
 */
@Data
public class UpdateTaskInfoPO {

    @NotNull(message = "任务id不能为空")
    private Integer id;

    /**
     * 业务id
     */
    @NotNull(message = "业务id不能为空")
    private Integer businessId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 员工id
     */
    @NotNull(message = "请选择员工")
    private Integer staffId;

    /**
     * 员工名称
     */
    @NotBlank(message = "员工id不能为空")
    private String staffName;

    /**
     * 描述
     */
    private String remark;

    /**
     * 员工提成
     */
    private String money;

    /**
     * 业务地址
     */
    private String businessAddress;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 业务图
     */
    private String businessFile;

    /**
     * 零件名字
     */
    private String partName;
}
