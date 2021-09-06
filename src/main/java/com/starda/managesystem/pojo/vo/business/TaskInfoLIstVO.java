package com.starda.managesystem.pojo.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.business
 * @ClassName: TeaskInfoLIstVO
 * @Author: chenqiu
 * @Description: 任务详情列表
 * @Date: 2021/9/4 13:33
 * @Version: 1.0
 */

@Data
public class TaskInfoLIstVO {

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
     * 业务图
     */
    private String businessFile;

    /**
     * 零件名字
     */
    private String partName;

    /**
     * 创建人名
     */
    private String createUser;


    /**
     * 0.未结束，1.终止  2. 未到款  3.完成
     */
    private Integer finish;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date finishTime;

    /**
     * 管理员备注
     */
    private TaskInfoRemarkVO manageRemarkVO;

}
