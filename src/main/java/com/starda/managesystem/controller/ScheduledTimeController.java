package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.RemoveIdsPO;
import com.starda.managesystem.pojo.po.reminder.InsertReminderPO;
import com.starda.managesystem.pojo.po.reminder.ReminderQueryPO;
import com.starda.managesystem.pojo.po.reminder.ReminderUpdatePO;
import com.starda.managesystem.pojo.vo.ReminderListVO;
import com.starda.managesystem.service.IScheduledTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: ShoudrlTimeController
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/31 0:14
 * @Version: 1.0
 */

@RestController
@RequestMapping("/scheduled/time")
public class ScheduledTimeController {

    @Autowired
    private IScheduledTimeService scheduledTimeService;

    /**
     * 添加消息
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("/insertReminderInfo")
    public Result insertReminderInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid InsertReminderPO po) throws Exception{

        this.scheduledTimeService.insertReminderInfo(user, po);

        return Result.success();
    }

    /**
     * 修改
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("/updateReminderInfo")
    public Result updateReminderInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid ReminderUpdatePO po) throws Exception{

        this.scheduledTimeService.updateReminderInfo(user, po);

        return Result.success();
    }

    /**
     * 消息列表
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("/getReminderInfoList")
    public Result getReminderInfoList(@AnnotationAuthor UserVO user, @RequestBody @Valid ReminderQueryPO po) throws Exception{

        Result<ReminderListVO> reminderList = this.scheduledTimeService.getReminderList(user, po);

        return reminderList;
    }

    /**
     * 修改消息
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    @PostMapping("/removeReminderInfo")
    public Result removeReminderInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid RemoveIdsPO po) throws Exception{

        this.scheduledTimeService.removeReminderInfo(user, po);

        return Result.success();
    }

    /**
     * 获取详情
     * @param user
     * @param reminderId
     * @return
     * @throws Exception
     */
    @PostMapping("/getReminderInfo/{reminderId}")
    public Result getReminderInfo(@AnnotationAuthor UserVO user, @PathVariable @NotNull(message = "选择查看对象") Integer reminderId) throws Exception{

        ReminderListVO reminderInfo = this.scheduledTimeService.getReminderInfo(user, reminderId);

        return Result.ok().resultPage(reminderInfo);
    }

}
