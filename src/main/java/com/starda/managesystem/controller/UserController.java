package com.starda.managesystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.annotation.AnnotationAuthor;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.UserInfo;
import com.starda.managesystem.pojo.po.staff.*;
import com.starda.managesystem.pojo.vo.staff.AccountInfoListVO;
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
     * 账号列表
     * @param userVO
     * @param accountListPO
     * @return
     * @throws Exception
     */
    @PostMapping("/getAccountList")
    public Result getAccountList(@AnnotationAuthor UserVO userVO, @RequestBody @Valid AccountListPO accountListPO) throws Exception{

        IPage<AccountInfoListVO> page = this.userInfoService.getAccountList(userVO, accountListPO);

        return Result.ok().resultPage(page.getRecords(), page.getCurrent(), page.getSize(), page.getTotal());
    }

    /**
     * 账号列表
     * @param userVO
     * @param accountIds
     * @return
     * @throws Exception
     */
    @PostMapping("/removeAccountList/{accountIds}/{type}")
    public Result removeAccountList(@AnnotationAuthor UserVO userVO, @PathVariable @NotBlank(message = "请选择删除账号") String accountIds, @PathVariable("type")Integer type) throws Exception{

        List<String> accountIdList = new ArrayList<String>(Arrays.asList(accountIds.split(",")));

        this.userInfoService.removeAccountList(userVO, accountIdList.stream().map(accountId->Integer.valueOf(accountId)).collect(Collectors.toList()), type);

        return Result.success();
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

    /**
     * 修改员工信息
     * @param userVO
     * @param staffInfo
     * @return
     * @throws Exception
     */
    @PostMapping("/updateStaffInfo")
    public Result updateStaffInfo(@AnnotationAuthor UserVO userVO, @RequestBody @Valid StaffInfoUpdatePO staffInfo) throws Exception{

        this.userInfoService.updateStaffInfo(userVO, staffInfo);

        return Result.success();
    }

    /**
     * 修改 账号信息
     * @param accountInfoPO
     * @return
     * @throws Exception
     */
    @PostMapping("/updateAccountInfo")
    public Result updateAccountInfo(@AnnotationAuthor UserVO user, @RequestBody @Valid AccountInfoPO accountInfoPO) throws Exception{

        this.userInfoService.updateAccountInfo(user, accountInfoPO);

        return Result.success();
    }

    /**
     * 修改密码
     * @param userVO
     * @param passwordPO
     * @return
     * @throws Exception
     */
    @PostMapping("/updateAccountPassword")
    public Result updateAccountPassword(@AnnotationAuthor UserVO userVO, @RequestBody @Valid AccountPasswordPO passwordPO) throws Exception{

        this.userInfoService.updateAccountPassword(userVO, passwordPO);

        return Result.success();
    }

}
