package com.starda.managesystem.controller;

import com.starda.managesystem.service.IBusinessTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
