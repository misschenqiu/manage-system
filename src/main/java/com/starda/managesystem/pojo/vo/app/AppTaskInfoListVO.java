package com.starda.managesystem.pojo.vo.app;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.app
 * @ClassName: AppTaskInfoListVO
 * @Author: chenqiu
 * @Description: app员工任务信息
 * @Date: 2021/9/8 22:46
 * @Version: 1.0
 */

@Data
public class AppTaskInfoListVO {

    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 员工提成
     */
    private String money;

    /**
     * 创建人名
     */
    private String createUser;

    /**
     * 0.未结束，1.终止  2. 未到款  3.完成 4.退回 5.驳回
     */
    private Integer finish;

    /**
     * 员工是否确认 0.否 1.是
     */
    private Integer staffConfirm;

}
