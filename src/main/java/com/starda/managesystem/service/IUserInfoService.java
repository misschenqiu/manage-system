package com.starda.managesystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.SysUser;
import com.starda.managesystem.pojo.UserInfo;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: IUserInfoService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/7/29 11:38
 * @Version: 1.0
 */
public interface IUserInfoService {

    @Deprecated
    Object getUserInfo();

    /**
     * 获取账号信息
     * @param account 账号 账号绑定电话号码 凭证
     * @return
     */
    AccountInfoDTO getAccountInfo(String account);

    /**
     * 获取到账号信息 列表
     * @return
     */
    List<System> getAccountInfoList();


    void insertStaffInfo() throws Exception;

}
