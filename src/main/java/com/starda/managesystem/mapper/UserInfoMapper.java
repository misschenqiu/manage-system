package com.starda.managesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starda.managesystem.pojo.SysUser;
import com.starda.managesystem.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息管理
 */

@Mapper
@Deprecated
public interface UserInfoMapper  extends BaseMapper<UserInfo>{
}