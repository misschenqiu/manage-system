package com.starda.managesystem.pojo.vo.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.app
 * @ClassName: AppBusinessInfoListVO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/17 13:00
 * @Version: 1.0
 */

@Data
public class AppBusinessInfoListVO {

    private Integer id;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 员工名称
     */
    private String staffName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 创建人名
     */
    private String createUser;


    /**
     * 0.未结束，1.终止  2. 未到款  3.完成 4.退回 5.驳回
     */
    private Integer finish;

    /**
     * 确认下发0.否 1.是
     */
    private Integer confirmIssue;

    /**
     * 员工是否已经提交 0.否 1.是
     */
    private Integer staffSubmit;

}
