package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.CommonParamPO;
import com.starda.managesystem.pojo.po.CommonUpdateIdPO;
import com.starda.managesystem.pojo.po.app.AppConfirmTaskPO;
import com.starda.managesystem.pojo.po.app.AppManageQueryBusinessPO;
import com.starda.managesystem.pojo.po.app.AppTaskQueryPO;
import com.starda.managesystem.pojo.po.business.ConfirmTaskPO;
import com.starda.managesystem.pojo.po.business.InsertTaskInfoPO;
import com.starda.managesystem.pojo.po.business.TaskInfoQueryPO;
import com.starda.managesystem.pojo.vo.app.AppBusinessInfoListVO;
import com.starda.managesystem.pojo.vo.app.AppBusinessListVO;
import com.starda.managesystem.pojo.vo.app.AppTaskInfoListVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoLIstVO;
import com.starda.managesystem.pojo.vo.business.TaskInfoVO;
import com.starda.managesystem.service.IAppManageService;
import com.starda.managesystem.service.IAppTaskBusinessService;
import com.starda.managesystem.service.IBusinessTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: AppTaskController
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/1 15:13
 * @Version: 1.0
 */

@RestController
@RequestMapping("/app/business")
public class AppTaskController {

    @Autowired
    private IAppTaskBusinessService appTaskBusinessService;

    @Autowired
    private IBusinessTaskService taskService;

    @Autowired
    private IAppManageService appManageService;

    @Autowired
    private IBusinessTaskService businessTaskService;

    /**
     * 获取app首页数据
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("getTaskInfoList")
    public Result getTaskInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid AppTaskQueryPO po) throws Exception{

        Result<AppTaskInfoListVO> taskInfoList = this.appTaskBusinessService.getTaskInfoList(user, po);

        return taskInfoList;
    }

    /**
     * 确认业务信息
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("confirmTaskInfo")
    public Result confirmTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid AppConfirmTaskPO po) throws Exception{

        this.appTaskBusinessService.confirmTaskInfo(user, po);

        return Result.success();
    }

    /**
     * 查阅 任务详情
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("getTaskInfo")
    public Result getTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid ConfirmTaskPO po) throws Exception{

        TaskInfoVO taskInfoVO = this.taskService.getTaskInfo(user, po);

        return Result.ok().resultPage(taskInfoVO);
    }

    /**
     * 创建下发任务
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("issueTaskInfo")
    public Result issueTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid InsertTaskInfoPO po) throws Exception{

        return Result.success();
    }

    /**
     *
     * @param user
     * @param type
     * @return
     * @throws Exception
     */
    @PostMapping("manageOrStaff")
    public Result manageOrStaff(@AnnotationAuthor UserVO user, @RequestBody @Valid Integer type) throws Exception{
        return Result.success();
    }

    /**
     * 接收任务
     * @param user
     * @param businessId
     * @return
     * @throws Exception
     */
    @PostMapping("/confirmBusinessInfo/{businessId}")
    public Result confirmBusinessInfo(@AnnotationAuthor UserVO user, @PathVariable @Valid Integer businessId) throws Exception{

        this.appTaskBusinessService.confirmBusinessInfo(user, businessId);

        return Result.success();
    }

    /**
     * 添加手机序列号
     * @param user
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/phoneSerial")
    public Result phoneSerial(@AnnotationAuthor UserVO user, @RequestBody @Valid CommonParamPO param) throws Exception{

        this.appTaskBusinessService.phoneSerial(user, param);

        return Result.success();
    }

    /**
     * 确认或驳回任务
     * @param user
     * @param confirm
     * @return
     * @throws Exception
     */
    @PostMapping("confirmIssueTaskInfo")
    public Result confirmIssueTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid ConfirmTaskPO confirm) throws Exception{

        this.businessTaskService.confirmTaskInfo(user, confirm);

        return Result.success();
    }

    /**
     * 管理员任务列表
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("getManageTaskInfoList")
    public Result getManageTaskInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid TaskInfoQueryPO po) throws Exception{

        Result<AppBusinessInfoListVO> appTaskInfoList = this.appManageService.getAppTaskInfoList(user, po);

        return appTaskInfoList;
    }

    /**
     * 下发任务 至员工
     * @param user
     * @param taskInfo
     * @return
     * @throws Exception
     */
    @PostMapping("issueTaskToStaff")
    public Result issueTaskToStaff(@AnnotationAuthor UserVO user, @RequestBody @Valid CommonUpdateIdPO taskInfo) throws Exception{

        this.businessTaskService.confirmIssueTask(user, taskInfo);

        return Result.success();
    }

    /**
     * 业务列表
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("getBusinessList")
    public Result getBusinessList(@AnnotationAuthor UserVO user, @RequestBody @Valid AppManageQueryBusinessPO po) throws Exception{

        Result<AppBusinessListVO> appBusinessListVOResult = appManageService.getBusinessList(user, po);

        return appBusinessListVOResult;
    }

}
