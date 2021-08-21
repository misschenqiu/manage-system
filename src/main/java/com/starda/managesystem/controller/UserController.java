package com.starda.managesystem.controller;

import com.starda.managesystem.pojo.UserInfo;
import com.starda.managesystem.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@Valid UserInfo userInfo){
        return userInfoService.getUserInfo();
    }

}
