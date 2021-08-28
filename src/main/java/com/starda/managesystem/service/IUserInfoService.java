package com.starda.managesystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
import com.starda.managesystem.pojo.po.staff.StaffInfoPO;
import com.starda.managesystem.pojo.po.staff.StaffQueryPO;
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
     * 获取账号信息
     * @param account 账号 账号绑定电话号码 凭证
     * @return
     */
    AccountInfoDTO getAccountInfo(String account);

    /**
     * 获取到账号信息 列表
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

}
