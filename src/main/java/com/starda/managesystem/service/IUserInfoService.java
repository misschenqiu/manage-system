package com.starda.managesystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
import com.starda.managesystem.pojo.po.staff.*;
import com.starda.managesystem.pojo.vo.staff.AccountInfoListVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoListVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoVO;

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
     * 获取账号信息列表
     * @param user
     * @param accountListPO
     * @return
     * @throws Exception
     */
    IPage<AccountInfoListVO> getAccountList(UserVO user, AccountListPO accountListPO) throws Exception;

    /**
     * 删除啊账号信息
     * @param user
     * @param accountIds
     * @throws Exception
     */
    void removeAccountList(UserVO user, List<Integer> accountIds, Integer type) throws Exception;

    /**
     * 获取账号信息
     * @param account 账号 账号绑定电话号码 凭证
     * @return
     */
    AccountInfoDTO getAccountInfo(String account);

    /**
     * 获取到 员工 信息 列表
     * @return
     */
    IPage<StaffInfoListVO> getAccountInfoList(UserVO user, StaffQueryPO po) throws Exception;

    /**
     * 添加员工信息
     * @param user
     * @param staffInfo
     * @throws Exception
     */
    void insertStaffInfo(UserVO user, StaffInfoPO staffInfo) throws Exception;

    /**
     * 获取员工详情
     * @param user
     * @return
     * @throws Exception
     */
    StaffInfoVO getStaffInfo(UserVO user, Integer staffId) throws Exception;

    /**
     * 删除员工并收回账号
     * @param staffIds
     * @throws Exception
     */
    void removeStaffInfoList(List<Integer> staffIds) throws Exception;

    /**
     * 修改员工信息
     * @param userVO
     * @param staffInfo
     * @throws Exception
     */
    void updateStaffInfo(UserVO userVO, StaffInfoUpdatePO staffInfo) throws Exception;

    /**
     * 修改账号信息
     * @param accountInfoPO
     * @throws Exception
     */
    void updateAccountInfo(UserVO user, AccountInfoPO accountInfoPO) throws Exception;

    /**
     * 修改密码
     * @param user
     * @param password
     * @throws Exception
     */
    void updateAccountPassword(UserVO user, AccountPasswordPO password) throws Exception;

}
