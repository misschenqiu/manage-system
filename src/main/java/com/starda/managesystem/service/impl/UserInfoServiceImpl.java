package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.NumberUtil;
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
import com.starda.managesystem.pojo.dto.RoleListDTO;
import com.starda.managesystem.pojo.dto.role.RoleDTO;
import com.starda.managesystem.pojo.dto.staff.StaffDTO;
import com.starda.managesystem.pojo.po.staff.*;
import com.starda.managesystem.pojo.vo.role.RoleListVO;
import com.starda.managesystem.pojo.vo.staff.AccountInfoListVO;
import com.starda.managesystem.pojo.vo.staff.AccountInfoVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoListVO;
import com.starda.managesystem.pojo.vo.staff.StaffInfoVO;
import com.starda.managesystem.service.IAddressService;
import com.starda.managesystem.service.ISysRoleService;
import com.starda.managesystem.service.IUserInfoService;
import com.starda.managesystem.util.AES;
import com.starda.managesystem.util.BeanCopyUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: UserInfoServiceImpl
 * @Author: chenqiu
 * @Description: ???????????? ?????????
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

    @Autowired
    private IAddressService addressService;

    @Override
    public Object getUserInfo() {
        return userInfoMapper.selectById(2);
    }

    @Override
    public IPage<AccountInfoListVO> getAccountList(UserVO user, AccountListPO accountListPO) throws Exception {

        if (user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            accountListPO.setAccountId(user.getId());
        }

        // ??????????????????
        IPage<AccountInfoListVO> accountInfoListVOIPage = this.getBaseMapper().getAccountList(new Page<AccountInfoListVO>(accountListPO.getCurrentPage(), accountListPO.getPageSize()), accountListPO);

        // ?????????????????????
        List<Integer> accountIds = accountInfoListVOIPage.getRecords().stream().map(account->account.getId()).collect(Collectors.toList());
        List<RoleListDTO> roleByAccountIdsList = this.roleService.getRoleByAccountIdsList(accountIds);

        // ????????????
        if(null == roleByAccountIdsList || roleByAccountIdsList.isEmpty()){
            log.info("???????????????????????????");
            return accountInfoListVOIPage;
        }

        Map<Integer, List<RoleListDTO>> collect = roleByAccountIdsList.stream().collect(Collectors.groupingBy(RoleListDTO::getAccountId));

        // ????????????
        accountInfoListVOIPage.getRecords().stream().forEach(account->{
            account.setRoleListVOList(BeanUtil.copyToList(collect.get(account.getId()), RoleListVO.class));
            // ????????????
            account.setPhone(DesensitizedUtil.mobilePhone(account.getPhone()));
        });
        log.info("????????????????????????");
        return accountInfoListVOIPage;
    }

    @Override
    public AccountInfoVO getAccountUserInfo(UserVO user, Integer accountId) throws Exception {
        // ??????????????????
        AccountInfoVO accountInfoVO = new AccountInfoVO();
        if(accountId == null || accountId < Constant.BaseNumberManage.ZERO){
            accountInfoVO = this.getBaseMapper().getAccountUserInfo(user.getId());
        }else{
            accountInfoVO = this.getBaseMapper().getAccountUserInfo(accountId);
        }
        // ?????????????????????
        List<Integer> accountIds = new ArrayList<Integer>();
        accountIds.add(accountInfoVO.getId());
        // ??????????????????
        List<RoleListDTO> roleByAccountIdsList = this.roleService.getRoleByAccountIdsList(accountIds);
        accountInfoVO.setRoleListVOList(BeanUtil.copyToList(roleByAccountIdsList, RoleListVO.class));
        return accountInfoVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeAccountList(UserVO user, List<Integer> accountIds, Integer type) throws Exception {
        // ???????????????????????????
        if (accountIds.contains(user.getId())) {
            throw new ManageStarException("??????????????????????????????");
        }
        // 1.????????????
        List<SysUser> userList = new ArrayList<SysUser>();
        accountIds.stream().forEach(userId -> {
            SysUser account = new SysUser();
            account.setId(userId);
            account.setStatus(Constant.BaseNumberManage.ZERO);
            userList.add(account);
        });
        this.updateBatchById(userList);
        // 2.???????????????
        if (type != null && type.equals(Constant.BaseNumberManage.ONE)) {
            this.staffMapper.updateByAccountIds(accountIds);
        }

    }

    @Override
    public AccountInfoDTO getAccountInfo(String account) {
        AccountInfoDTO accountInfo;
        // ??????????????????
        if (NumberUtil.isNumber(account)) {
            accountInfo = this.getBaseMapper().getAccountInfo("", AES.encrypt(account));
        } else {
            accountInfo = this.getBaseMapper().getAccountInfo(account, "");
        }
        if (null == accountInfo) {
            return null;
        }
        // ??????????????????
        MPJLambdaWrapper<SysRole> wrapper = new MPJLambdaWrapper<SysRole>();
        List<RoleDTO> roleDtoList = this.sysRoleMapper.selectJoinList(RoleDTO.class, wrapper
                .selectAll(SysRole.class)
                .rightJoin(SysUserRole.class, SysUserRole::getRole_id, SysRole::getId)
                .eq(SysUserRole::getUser_id, accountInfo.getId())
                .eq(SysRole::getStatus, Constant.BaseNumberManage.ONE)
                .eq(SysUserRole::getStatus, Constant.BaseNumberManage.ONE));
        // ????????????
        HashMap<String,String> mapping = new HashMap<String, String>();
        mapping.put("roleName", "role_name");
        mapping.put("createTime", "create_time");
        mapping.put("modifiedTime", "modified_time");
        mapping.put("addressCode", "address_code");
        mapping.put("roleCode", "role_code");
        mapping.put("createAccountId", "create_account_id");
        List<SysRole> roleList = BeanCopyUtil.copyToList(roleDtoList, SysRole.class, mapping);
        accountInfo.setRoleList(roleList);
        return accountInfo;
    }

    @Override
    public IPage<StaffInfoListVO> getAccountInfoList(UserVO user, StaffQueryPO po) throws Exception {

        // ???????????????????????????
        if (!user.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)) {
            po.setAccountId(user.getId());
        }

        IPage<StaffInfoListVO> staffList = this.staffMapper.getAccountInfoList(new Page<StaffInfoListVO>(po.getCurrentPage(), po.getPageSize()), po);

        // ??????????????????
        staffList.getRecords().stream().forEach(staff -> {
            if (po.getAccountId() != null) {
                staff.setAccountName(user.getStaffName());
            }
            // ????????????
            staff.setPhone(DesensitizedUtil.mobilePhone(staff.getPhone()));
            staff.setIdentity(DesensitizedUtil.idCardNum(staff.getIdentity(), 3, 4));
        });

        return staffList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertStaffInfo(UserVO user, StaffInfoPO staffInfo) throws Exception {
        // ??????????????????????????????
        if(NumberUtil.isNumber(staffInfo.getAccount())){
            throw new ManageStarException("????????????????????????????????????????????????????????????????????????");
        }

        // ????????????
        if(StrUtil.isNotBlank(staffInfo.getAddress())){
            staffInfo.setAddressCode(this.addressService.addManageAddress(staffInfo.getAddress()));
        }
        // 1.????????????????????????
        List<SysUser> userList = this.getBaseMapper().selectList(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, staffInfo.getAccount()));
        if (null != userList && !userList.isEmpty()) {
            throw new ManageStarException(ExceptionEnums.USER_ACCOUNT_DISABLE.getCode(), ExceptionEnums.USER_ACCOUNT_DISABLE.getMessage());
        }
        // 2???????????????
        SysUser sysUser = new SysUser();
        sysUser.setStatus(Constant.BaseNumberManage.ONE);
        sysUser.setAccount(staffInfo.getAccount());
        sysUser.setAddress(staffInfo.getAddress());
        sysUser.setPassword(SecurityPasswordCommon.password(staffInfo.getPassword()));
        sysUser.setPhone(staffInfo.getPhone());
        sysUser.setAddress_code(staffInfo.getAddressCode());
        this.getBaseMapper().insertSelective(sysUser);

        // 3?????????????????????
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("userHeight", "user_height");
        mapping.put("headImg", "head_img");
        mapping.put("userName", "username");
        SysStaff staff = BeanCopyUtil.toBean(staffInfo, SysStaff.class, mapping);
        staff.setStatus(Constant.BaseNumberManage.ONE);
        staff.setCreate_account_id(user.getId());
        staff.setUser_id(sysUser.getId());
        staff.setCreate_time(new Date());
        this.staffMapper.insertSelective(staff);
        log.info("??????????????????");

        // 4.??????????????????
        if (null == staffInfo.getRoleIds() || staffInfo.getRoleIds().isEmpty()) {
            return;
        }

        this.roleService.insertRoleUserList(sysUser.getId(), staffInfo.getRoleIds());
    }

    @Override
    public StaffInfoVO getStaffInfo(UserVO user, Integer staffId) throws Exception {

        if (staffId == null || staffId < 0) {
            staffId = user.getStaffId();
        }

        // ????????????
        StaffInfoVO staffInfoVO = this.getBaseMapper().getStaffInfo(staffId);

        // ????????????
        List<RoleListDTO> roleList = this.roleService.getRoleList(staffInfoVO.getUserId());

        staffInfoVO.setRoleList(BeanUtil.copyToList(roleList, RoleListVO.class));

        return staffInfoVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeStaffInfoList(List<Integer> staffIds) throws Exception {

        if (null == staffIds || staffIds.isEmpty()) {
            return;
        }
        // 0.?????????????????????
        List<StaffDTO> staffs = this.staffMapper.selectJoinList(StaffDTO.class, new MPJLambdaWrapper<SysStaff>().selectAll(SysStaff.class).in(SysStaff::getId, staffIds));
        // 1.???????????????
        this.staffMapper.updateByStaffIds(staffIds);
        // 2.???????????????
        List<Integer> accountIds = staffs.stream().map(staff -> staff.getUserId()).collect(Collectors.toList());

        List<SysUser> userList = new ArrayList<SysUser>();
        accountIds.stream().forEach(userId -> {
            SysUser account = new SysUser();
            account.setId(userId);
            account.setStatus(Constant.BaseNumberManage.ZERO);
            userList.add(account);
        });

        this.updateBatchById(userList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStaffInfo(UserVO userVO, StaffInfoUpdatePO staffInfo) throws Exception {

        // ????????????
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("userHeight", "user_height");
        mapping.put("headImg", "head_img");
        mapping.put("userName", "username");
        SysStaff staff = BeanCopyUtil.toBean(staffInfo, SysStaff.class, mapping);
        staff.setUpdate_time(new Date());
        // ????????????
        if (staffInfo.getId() == null) {
            staff.setId(userVO.getId());
        } else {
            // ??????????????????????????????
            List<SysStaff> staffs = this.staffMapper.selectList(new LambdaQueryWrapper<SysStaff>().eq(SysStaff::getId, staffInfo.getId()));

            if (null == staffs || staffs.isEmpty()) {
                throw new ManageStarException(ExceptionEnums.USER_ACCOUNT_DISABLE.getCode(), ExceptionEnums.USER_ACCOUNT_DISABLE.getMessage());
            }

            if(StrUtil.isNotBlank(staff.getPhone())){
                SysUser user = new SysUser();
                user.setPhone(staff.getPhone());
                user.setId(staffs.get(Constant.BaseNumberManage.ZERO).getUser_id());
                this.updateById(user);
            }

        }

        this.staffMapper.updateByPrimaryKeySelective(staff);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAccountInfo(UserVO user, AccountInfoPO accountInfoPO) throws Exception {
        // ????????????
        if(StrUtil.isNotBlank(accountInfoPO.getAddress())){
            accountInfoPO.setAddressCode(this.addressService.addManageAddress(accountInfoPO.getAddress()));
        }
        // ??????????????????
        if (user.getId().equals(accountInfoPO.getAccountId())) {
            throw new ManageStarException(ExceptionEnums.USER_ACCOUNT_NOT_OPERATION.getCode(), ExceptionEnums.USER_ACCOUNT_NOT_OPERATION.getMessage());
        }
        // ??????????????????
        SysUser account = new SysUser();
        account.setAddress(accountInfoPO.getAddress());
        account.setAddress_code(accountInfoPO.getAddressCode());
        account.setId(accountInfoPO.getAccountId());
        this.getBaseMapper().updateByPrimaryKeySelective(account);

        if(null == accountInfoPO.getRoleIds() || accountInfoPO.getRoleIds().isEmpty()){
            return;
        }

        // ??????????????????
        this.roleService.removeRoleUserByAccountIds(accountInfoPO.getAccountId());
        // ??????
        this.roleService.insertRoleUserList(accountInfoPO.getAccountId(), accountInfoPO.getRoleIds());

    }

    @Override
    public void updateAccountPassword(UserVO user, AccountPasswordPO password) throws Exception {

        // ????????????????????????
        if (!password.getNewPassword().equals(password.getAgainPassword())) {
            throw new ManageStarException(ExceptionEnums.USER_ACCOUNT_AGAIN_PASSWORD.getCode(), ExceptionEnums.USER_ACCOUNT_AGAIN_PASSWORD.getMessage());
        }

        // ??????????????????????????????
        SysUser sysUser = this.getBaseMapper().selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, user.getId()));
        if (!SecurityPasswordCommon.isPassword(password.getOldPassword(), sysUser.getPassword())) {
            throw new ManageStarException(ExceptionEnums.USER_CREDENTIALS_ERROR.getCode(), ExceptionEnums.USER_CREDENTIALS_ERROR.getMessage());
        }

        SysUser account = new SysUser();
        account.setId(user.getId());
        account.setPassword(SecurityPasswordCommon.password(password.getNewPassword()));

        this.getBaseMapper().updateByPrimaryKeySelective(account);
    }

}
