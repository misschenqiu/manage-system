package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.app.AppConfirmTaskPO;
import com.starda.managesystem.pojo.po.app.AppTaskQueryPO;
import com.starda.managesystem.pojo.po.business.ConfirmTaskPO;
import com.starda.managesystem.pojo.po.business.InsertTaskInfoPO;
import com.starda.managesystem.service.IAppTaskBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取app首页数据
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("getTaskInfoList")
    public Result getTaskInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid AppTaskQueryPO po) throws Exception{

        return Result.success();
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

        return Result.success();
    }

    /**
     * 下发任务
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("issueTaskInfo")
    public Result issueTaskInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid InsertTaskInfoPO po) throws Exception{

        return Result.success();
    }

}
