package com.starda.managesystem.pojo.po.reminder;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.reminder
 * @ClassName: InsertReminderPO
 * @Author: chenqiu
 * @Description: 添加单位信息
 * @Date: 2021/8/31 20:43
 * @Version: 1.0
 */

@Data
public class InsertReminderPO {

    /**
     * 消息名称
     */
    private String reminderName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 是否开启 0 否  1.是
     */
    private Integer reminderOpen;

    /**
     * 重复提醒次数
     */
    private Integer againNumber;

    /**
     * 创建人信息
     */
    private String createUserName;

    /**
     * 是否重复提醒 0.否 1.是
     */
    private Integer again;

    /**
     * 描述
     */
    private String remark;

    /**
     * 单位信息
     *
     */
    private List<Integer> companyIds;

}
