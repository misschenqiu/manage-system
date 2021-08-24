package com.starda.managesystem.controller;

import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
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

@RequestMapping("/account")
@RestController
public class UserController {

    @Autowired
    private IUserInfoService userInfoService;

    @PostMapping("/getUserInfo")
    public Object getUserInfo(@AnnotationAuthor UserVO userVO, @Valid UserInfo userInfo){
        System.out.println(userVO);
        return userInfoService.getUserInfo();
    }

    /**
     * 添加员工 账号信息
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/staff/insertStaff")
    public Result addStaffInfo(@AnnotationAuthor UserVO userVO) throws Exception{

        this.userInfoService.insertStaffInfo();

        return Result.success();
    }

}
