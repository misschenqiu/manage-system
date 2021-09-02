package com.starda.managesystem.controller;

import com.starda.managesystem.service.IAppTaskBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
