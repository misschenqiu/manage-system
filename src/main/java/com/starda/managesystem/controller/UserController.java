package com.starda.managesystem.controller;

import com.starda.managesystem.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.controller
 * @ClassName: UserController
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/7/29 17:41
 * @Version: 1.0
 */

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping
    @ResponseBody
    public Object getUserInfo(){
        return userInfoService.getUserInfo();
    }

}
