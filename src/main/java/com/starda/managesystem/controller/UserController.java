package com.starda.managesystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.UserInfo;
import com.starda.managesystem.pojo.po.staff.StaffInfoPO;
import com.starda.managesystem.pojo.po.staff.StaffQueryPO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoListVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoVO;
import com.starda.managesystem.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param po 员工信息
     * @return
     * @throws Exception
     */
    @PostMapping("/staff/insertStaff")
    public Result addStaffInfo(@AnnotationAuthor UserVO userVO, @RequestBody @Valid StaffInfoPO po) throws Exception{

        this.userInfoService.insertStaffInfo(userVO, po);

        return Result.success();
    }

    /**
     * 获取到用户信息
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/staff/getStaffInfo/{staffId}")
    public Result getStaffInfo(@AnnotationAuthor UserVO userVO, @PathVariable Integer staffId) throws Exception{

        StaffInfoVO staffInfoVO = this.userInfoService.getStaffInfo(userVO, staffId);

        return Result.ok().resultPage(staffInfoVO);
    }

    /**
     * 获取到员工信息列表
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/staff/getStaffInfoList")
    public Result getStaffInfoList(@AnnotationAuthor UserVO userVO, @RequestBody @Valid StaffQueryPO po) throws Exception{

        IPage<StaffInfoListVO> page = this.userInfoService.getAccountInfoList(userVO, po);

        return Result.ok().resultPage(page.getRecords(), page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 删除到员工信息列表 并收回账号
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/staff/removeStaffInfoList/{staffIds}")
    public Result removeStaffInfoList(@AnnotationAuthor UserVO userVO, @PathVariable @NotBlank(message = "请选择删除员工") String staffIds) throws Exception{

        List<String> staffIdList = new ArrayList<String>(Arrays.asList(staffIds.split(",")));

        this.userInfoService.removeStaffInfoList(staffIdList.stream().map(staffId->Integer.valueOf(staffId)).collect(Collectors.toList()));

        return Result.success();
    }

    @PostMapping("/updateStaffInfo")
    public Result updateStaffInfo(@AnnotationAuthor UserVO userVO) throws Exception{

        return Result.success();
    }

    @PostMapping("/updateAccountInfo")
    public Result updateAccountInfo() throws Exception{
        return Result.success();
    }

    @PostMapping("/updateAccountPassword")
    public Result updateAccountPassword() throws Exception{

        return Result.success();
    }

}
