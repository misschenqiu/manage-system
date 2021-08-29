package com.starda.managesystem.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starda.managesystem.pojo.SysUser;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
import com.starda.managesystem.pojo.po.staff.AccountListPO;
import com.starda.managesystem.pojo.vo.staff.AccountInfoListVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 获取账号信息
     * @param account 账号或者电话号码
     * @param phone 电话号码
     * @return
     */
    AccountInfoDTO getAccountInfo(@Param("account") String account, @Param("phone") String phone);

    /**
     * 获取到员工详情
     * @param staffId
     * @return
     * @throws Exception
     */
    StaffInfoVO getStaffInfo(Integer staffId) throws Exception;

    /**
     * 获取账号列表信
     * @param page
     * @param accountListPO
     * @return
     * @throws Exception
     */
    IPage<AccountInfoListVO> getAccountList(Page<AccountInfoListVO> page, @Param("account") AccountListPO accountListPO) throws Exception;

}