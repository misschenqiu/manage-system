package com.starda.managesystem.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starda.managesystem.pojo.SysUser;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
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

}