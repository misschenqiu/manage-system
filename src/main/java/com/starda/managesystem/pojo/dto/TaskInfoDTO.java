package com.starda.managesystem.pojo.dto;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto
 * @ClassName: TaskInfoDTO
 * @Author: chenqiu
 * @Description: 重复发送任务信息实体类
 * @Date: 2021/9/22 23:35
 * @Version: 1.0
 */

@Data
public class TaskInfoDTO {

    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 手机序列号
     */
    private String phoneSerial;

    /**
     * 任务名称
     */
    private String taskName;



}
