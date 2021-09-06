package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.CommonIdsPO;
import com.starda.managesystem.pojo.po.business.*;
import com.starda.managesystem.pojo.vo.business.BusinessInfoListVO;
import com.starda.managesystem.pojo.vo.business.BusinessInfoVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoLIstVO;
import com.starda.managesystem.service.IBusinessTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: TaskController
 * @Author: chenqiu
 * @Description: 任务管理
 * @Date: 2021/8/27 22:35
 * @Version: 1.0
 */

@RestController
@RequestMapping("/task/business")
public class TaskController {

    @Autowired
    private IBusinessTaskService businessTaskService;

    /**
     * 下发任务
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/insertTaskInfo")
    public Result insertTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid InsertTaskInfoPO taskInfo) throws Exception{

        this.businessTaskService.insertTaskInfo(user, taskInfo);

        return Result.success();
    }

    /**
     * 删除任务
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/removeTaskInfo")
    public Result removeTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid CommonIdsPO taskInfo) throws Exception{

        this.businessTaskService.removeTaskInfo(user, taskInfo);

        return Result.success();
    }

    /**
     * 修改任务
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/updateTaskInfo")
    public Result updateTaskInfo(@AnnotationAuthor UserVO user) throws Exception{


        return Result.success();
    }

    /**
     * 任务列表
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/getTaskInfoList")
    public Result getTaskInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid TaskInfoQueryPO po) throws Exception{

        Result<TaskInfoLIstVO> taskInfoList = this.businessTaskService.getTaskInfoList(user, po);

        return taskInfoList;
    }

    /**
     * 任务详情
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/getTaskInfo")
    public Result getTaskInfo(@AnnotationAuthor UserVO user) throws Exception{


        return Result.success();
    }

    /**
     * 确认流程
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/confirmTaskInfo")
    public Result confirmTaskInfo(@AnnotationAuthor UserVO user) throws Exception{


        return Result.success();
    }

    /**
     * 流程图
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/getTaskInfoViw")
    public Result getTaskInfoViw(@AnnotationAuthor UserVO user) throws Exception{


        return Result.success();
    }


    /********************** 业务模块 ***************************/

    /**
     * 添加业务信息
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/insertBusinessInfo")
    public Result insertBusinessInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid InsertBusinessInfoPO businessInfo) throws Exception{

        this.businessTaskService.insertBusinessInfo(user, businessInfo);

        return Result.success();
    }

    /**
     * 修改业务详情
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/updateBusinessInfo")
    public Result updateBusinessInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid UpdateBusinessInfoPO updateBusinessInfo) throws Exception{

        this.businessTaskService.updateBusinessInfo(user, updateBusinessInfo);

        return Result.success();
    }

    /**
     * 删除当前业务信息
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/removeBusinessInfo")
    public Result removeBusinessInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid CommonIdsPO idList) throws Exception{

        this.businessTaskService.removeBusinessInfo(user, idList);

        return Result.success();
    }

    /**
     * 业务列表
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/queryBusinessInfoList")
    public Result queryBusinessInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid QueryBusinessInfoPO businessInfoPO) throws Exception{

        Result<BusinessInfoListVO> businessInfoList = this.businessTaskService.getBusinessInfoList(user, businessInfoPO);

        return businessInfoList;
    }

    /**
     * 业务详情
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/queryBusinessInfo/{businessId}")
    public Result queryBusinessInfo(@AnnotationAuthor UserVO user,@PathVariable @NotNull(message = "请选择业务") Integer businessId) throws Exception{

        BusinessInfoVO businessInfo = this.businessTaskService.getBusinessInfo(user, businessId);

        return Result.ok().resultPage(businessInfo);
    }

}
