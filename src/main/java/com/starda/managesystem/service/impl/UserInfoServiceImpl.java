package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.common.SecurityPasswordCommon;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.UserInfoMapper;
import com.starda.managesystem.mapper.system.SysRoleMapper;
import com.starda.managesystem.mapper.system.SysStaffMapper;
import com.starda.managesystem.mapper.system.SysUserMapper;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.SysStaff;
import com.starda.managesystem.pojo.SysUser;
import com.starda.managesystem.pojo.SysUserRole;
import com.starda.managesystem.pojo.dto.AccountInfoDTO;
import com.starda.managesystem.pojo.po.staff.StaffInfoPO;
import com.starda.managesystem.pojo.po.staff.StaffQueryPO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoListVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoVO;
import com.starda.managesystem.service.ISysRoleService;
import com.starda.managesystem.service.IUserInfoService;
import com.starda.managesystem.util.AES;
import com.starda.managesystem.util.BeanCopyUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
@Log4j2
public class UserInfoServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysStaffMapper staffMapper;

    @Autowired
    private ISysRoleService roleService;

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
        MPJLambdaWrapper<SysRole> wrapper = new MPJLambdaWrapper<SysRole>();
        List<SysRole> roleList = this.sysRoleMapper.selectJoinList(SysRole.class, wrapper
                                            .selectAll(SysRole.class)
                                            .select(SysRole::getId)
                                            .select(SysUserRole::getUser_id)
                                            .rightJoin(SysUserRole.class, SysUserRole::getRole_id, SysRole::getId)
                                            .eq(SysUserRole::getUser_id, accountInfo.getId())
                                            .eq(SysRole::getStatus, Constant.BaseNumberManage.ONE)
                                            .eq(SysUserRole::getStatus, Constant.BaseNumberManage.ONE));
        accountInfo.setRoleList(roleList);
        return accountInfo;
    }

    @Override
    public IPage<StaffInfoListVO> getAccountInfoList(UserVO user, StaffQueryPO po) throws Exception{

        // 判断是否是大管理员
        if(!user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            po.setAccountId(user.getId());
        }

        IPage<StaffInfoListVO> staffList = this.staffMapper.getAccountInfoList(new Page<StaffInfoListVO>(po.getCurrentPage(), po.getPageSize()), po);
        // 获取到创建人
        if(po.getAccountId() != null){
            staffList.getRecords().stream().forEach( staff->{
                staff.setAccountName(user.getStaffName());
            });
        }

        return staffList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertStaffInfo(UserVO user, StaffInfoPO staffInfo) throws Exception {
        // 1.判断账号是否存在
        List<SysUser> userList = this.getBaseMapper().selectList(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, staffInfo.getAccount()));
        if(null != userList || !userList.isEmpty()){
            throw new ManageStarException(ExceptionEnums.USER_ACCOUNT_DISABLE.getCode(), ExceptionEnums.USER_ACCOUNT_DISABLE.getMessage());
        }
        // 2，保存账号
        SysUser sysUser = new SysUser();
        sysUser.setStatus(Constant.BaseNumberManage.ONE);
        sysUser.setAccount(staffInfo.getAccount());
        sysUser.setAddress(staffInfo.getAddress());
        sysUser.setPassword(SecurityPasswordCommon.password(staffInfo.getPassword()));
        sysUser.setPhone(staffInfo.getPhone());
        sysUser.setAddress_code(staffInfo.getAddressCode());
        this.getBaseMapper().insertSelective(sysUser);

        // 3，保存员工信息
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("user_height", "userHeight");
        mapping.put("head_img", "headImg");
        mapping.put("username", "userName");
        SysStaff staff = BeanCopyUtil.toBean(staffInfo, SysStaff.class, mapping);
        staff.setStatus(Constant.BaseNumberManage.ONE);
        staff.setCreate_account_id(user.getId());
        staff.setUser_id(sysUser.getId());
        this.staffMapper.insertSelective(staff);
        log.info("添加员工成功");

        // 4.保存角色信息
        if(null == staffInfo.getRoleIds() || staffInfo.getRoleIds().isEmpty()){
            return;
        }
        Date time = new Date();
        List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
        staffInfo.getRoleIds().stream().forEach(role->{
            SysUserRole userRole = new SysUserRole();
            userRole.setRole_id(role);
            userRole.setUser_id(sysUser.getId());
            userRole.setStatus(Constant.BaseNumberManage.ONE);
            userRole.setCreate_time(time);
            userRoles.add(userRole);
        });
        this.roleService.insertRoleUser(userRoles);
    }

    @Override
    public StaffInfoVO getStaffInfo(UserVO user, Integer staffId) throws Exception{

        if(staffId == null || staffId < 0){
            return this.getBaseMapper().getStaffInfo(user.getStaffId());
        }

        return this.getBaseMapper().getStaffInfo(staffId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeStaffInfoList(List<Integer> staffIds) throws Exception{

        if(null == staffIds || staffIds.isEmpty()){
            return;
        }
        // 0.获取到员工信息
        List<SysStaff> staffs = this.staffMapper.selectList(new LambdaQueryWrapper<SysStaff>().in(SysStaff::getId, staffIds));
        // 1.删除员工表
        this.staffMapper.updateByStaffIds(staffIds);
        // 2.删除账号表
        List<Integer> accountIds = staffs.stream().map(staff->staff.getUser_id()).collect(Collectors.toList());

        List<SysUser> userList = new ArrayList<SysUser>();
        accountIds.stream().forEach(userId ->{
            SysUser account = new SysUser();
            account.setId(userId);
            account.setStatus(Constant.BaseNumberManage.ZERO);
            userList.add(account);
        });

        this.updateBatchById(userList);
    }

}
