package com.starda.managesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.mapper.UserInfoMapper;
import com.starda.managesystem.pojo.UserInfo;
import com.starda.managesystem.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: UserInfoServiceImpl
 * @Author: chenqiu
 * @Description: 用户实现类
 * @Date: 2021/7/29 11:39
 * @Version: 1.0
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
