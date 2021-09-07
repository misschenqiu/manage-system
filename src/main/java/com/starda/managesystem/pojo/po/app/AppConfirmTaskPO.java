package com.starda.managesystem.pojo.po.app;

import lombok.Data;

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
     * 确认属性 1.确认 2.退回
     */
    private Integer confirmType;

    /**
     * 描述
     */
    private String remark;

    /**
     * 业务图片
     */
    private String taskImg;

}
