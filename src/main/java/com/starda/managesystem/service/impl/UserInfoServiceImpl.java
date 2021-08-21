package com.starda.managesystem.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.mapper.UserInfoMapper;
import com.starda.managesystem.mapper.system.SysRoleMapper;
import com.starda.managesystem.mapper.system.SysUserMapper;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.SysUser;
import com.starda.managesystem.pojo.SysUserRole;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
import com.starda.managesystem.service.IUserInfoService;
import com.starda.managesystem.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: UserInfoServiceImpl
 * @Author: chenqiu
 * @Description: 账号信息 实现类
 * @Date: 2021/7/29 11:39
 * @Version: 1.0
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Object getUserInfo() {
        return userInfoMapper.selectById(2);
    }

    @Override
    public AccountInfoDTO getAccountInfo(String account) {
        AccountInfoDTO accountInfo;
        // 获取账号信息
        if (NumberUtil.isNumber(account)){
            accountInfo = this.getBaseMapper().getAccountInfo("", AES.encrypt(account));
        }else {
            accountInfo = this.getBaseMapper().getAccountInfo(account, "");
        }
        if(null == accountInfo){
            return null;
        }
        // 获取角色信息
        MPJLambdaWrapper<SysRole> wrapper = new MPJLambdaWrapper<>();
        List<SysRole> roleList = this.sysRoleMapper.selectList(wrapper.selectAll(SysRole.class)
                                             .rightJoin(SysUserRole.class, SysUserRole::getRole_id, SysRole::getId)
                                            .eq(SysUserRole::getUser_id, accountInfo.getId()));
        accountInfo.setRoleList(roleList);
        return accountInfo;
    }

    @Override
    public List<System> getAccountInfoList() {
        return null;
    }
}
