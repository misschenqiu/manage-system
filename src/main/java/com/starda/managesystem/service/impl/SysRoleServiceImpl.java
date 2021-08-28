package com.starda.managesystem.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.common.SecurityPasswordCommon;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.constant.Constant;
import com.starda.managesystem.exceptions.ManageStarException;
import com.starda.managesystem.mapper.system.SysRoleMapper;
import com.starda.managesystem.mapper.system.SysRoleMenuMapper;
import com.starda.managesystem.mapper.system.SysUserRoleMapper;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.SysRoleMenu;
import com.starda.managesystem.pojo.SysUserRole;
import com.starda.managesystem.pojo.dto.RoleListDTO;
import com.starda.managesystem.pojo.po.role.RoleInsertPO;
import com.starda.managesystem.pojo.po.role.RoleSelectPO;
import com.starda.managesystem.pojo.vo.role.RoleListVO;
import com.starda.managesystem.service.ISysRoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: SysRoleServiceImpl
 * @Author: chenqiu
 * @Description: 角色 管理
 * @Date: 2021/8/27 0:32
 * @Version: 1.0
 */

@Service
@Log4j2
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRole(UserVO user, RoleInsertPO po) throws Exception {
        // 1.检查名称和编码是否存在
        List<SysRole> roleList = this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRole_name, po.getRoleName())
                .eq(SysRole::getStatus, Constant.BaseNumberManage.ONE));
        if(null != roleList || !roleList.isEmpty()){
            throw new ManageStarException("角色名称已存在！");
        }

        // 2.保存角色
        SysRole role = new SysRole();
        role.setRole_code(Constant.BaseStringInfoManage.DEFT + SecurityPasswordCommon.getUuid());
        role.setAddress(po.getAddressName());
        role.setRole_name(po.getRoleName());
        role.setAddress_code(po.getAddressCode());
        role.setCreate_time(new Date());
        role.setRemark(po.getRemark());
        role.setCreate_account_id(user.getId());
        role.setStatus(Constant.BaseNumberManage.ONE);
        this.baseMapper.insertSelective(role);

        // 3.保存权限信息
        if(null == po.getMenuIds() || po.getMenuIds().isEmpty()){
            return;
        }
        List<SysRoleMenu> roleMenuList = new ArrayList<SysRoleMenu>();
        Date time = new Date();
        po.getMenuIds().stream().forEach(menuId ->{
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRole_id(role.getId());
            roleMenu.setMenu_id(menuId);
            roleMenu.setStatus(Constant.BaseNumberManage.ONE);
            roleMenu.setCreate_time(time);
            roleMenuList.add(roleMenu);
        });

        this.insertSysRoleMenuList(roleMenuList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleInfo(UserVO user, RoleInsertPO po) throws Exception {
        // 1.检查名称和编码是否存在
        List<SysRole> roleList = this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRole_name, po.getRoleName())
                .eq(SysRole::getStatus, Constant.BaseNumberManage.ONE));
        if(null != roleList || !roleList.isEmpty()){
            throw new ManageStarException("角色名称已存在！");
        }

        // 2.保存角色
        SysRole role = new SysRole();
        role.setId(po.getId());
        role.setAddress(po.getAddressName());
        role.setRole_name(po.getRoleName());
        role.setAddress_code(po.getAddressCode());
        role.setModified_time(new Date());
        role.setRemark(po.getRemark());
        role.setCreate_account_id(user.getId());
        role.setStatus(Constant.BaseNumberManage.ONE);
        this.baseMapper.updateByPrimaryKeySelective(role);

        // 3.保存权限信息
        if(null == po.getMenuIds() || po.getMenuIds().isEmpty()){
            return;
        }

        // 4.删除原来的数据
        this.deleteRoleMenuByRoleId(role.getId());

        // 5.添加数据
        List<SysRoleMenu> roleMenuList = new ArrayList<SysRoleMenu>();
        Date time = new Date();
        po.getMenuIds().stream().forEach(menuId ->{
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRole_id(role.getId());
            roleMenu.setMenu_id(menuId);
            roleMenu.setStatus(Constant.BaseNumberManage.ONE);
            roleMenu.setCreate_time(time);
            roleMenuList.add(roleMenu);
        });

        this.insertSysRoleMenuList(roleMenuList);

    }

    @Override
    public void deleteRole(List<Integer> roleId) throws Exception {
        // 修改权限
        this.baseMapper.updateRoleListById(roleId);
    }

    @Override
    public Result<RoleListVO> selectRoleList(UserVO userVO, RoleSelectPO po) throws Exception {

        // 判断是否是最大管理员
        if(userVO.getAuthorities().contains(Constant.BaseStringInfoManage.MANAGE)){
            po.setMaxManger(true);
        }

        Page<SysRole> sysRolePage = this.baseMapper.selectPage(new Page<SysRole>(po.getCurrentPage(), po.getPageSize()), new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getStatus, Constant.BaseNumberManage.ONE)
                .eq(po.getMaxManger(), SysRole::getCreate_account_id, userVO.getId())
                .orderByDesc(SysRole::getCreate_time));

        List<RoleListVO> roleListVOS = new ArrayList<RoleListVO>();
        sysRolePage.getRecords().stream().forEach(role->{
            roleListVOS.add(RoleListVO.builder()
                    .address(role.getAddress())
                    .id(role.getId())
                    .roleName(role.getRole_name())
                    .remark(role.getRemark())
                    .createTime(DateUtil.formatDateTime(role.getCreate_time()))
                    .build());
        });

        return Result.ok().resultPage(roleListVOS, sysRolePage.getCurrent(), sysRolePage.getSize(), sysRolePage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertSysRoleMenuList(List<SysRoleMenu> roleMenuList) throws Exception {
        if(null == roleMenuList || roleMenuList.isEmpty()){
            return;
        }
        this.roleMenuMapper.insertSysRoleMenuList(roleMenuList);
        log.info("添加中间表成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenuByRoleId(Integer roleId) throws Exception {
        if(roleId == null || roleId < 0){
            return;
        }
        this.roleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRole_id, roleId));
        log.info("删除中间表成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRoleUser(List<SysUserRole> userRoles) throws Exception{
        if(null == userRoles || userRoles.isEmpty()){
            return;
        }
        this.userRoleMapper.insertRoleUser(userRoles);
    }

    @Override
    public void removeRoleUserByAccountIds(Integer accountId) throws Exception {

        if(accountId == null || accountId < 0){
            throw new ManageStarException("账号id不能为空");
        }
        this.userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUser_id, accountId));
        log.info("删除角色中间表成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRoleUserList(Integer accountId, List<Integer> roleIds) throws Exception {
        Date time = new Date();
        List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
        roleIds.stream().forEach(role->{
            SysUserRole userRole = new SysUserRole();
            userRole.setRole_id(role);
            userRole.setUser_id(accountId);
            userRole.setStatus(Constant.BaseNumberManage.ONE);
            userRole.setCreate_time(time);
            userRoles.add(userRole);
        });
        this.insertRoleUser(userRoles);
    }

    @Override
    public List<RoleListDTO> getRoleList(Integer accountId) throws Exception{
        return this.baseMapper.selectRoleByAccountId(accountId);
    }

    @Override
    public List<RoleListDTO> getRoleByAccountIdsList(List<Integer> accountIds) throws Exception{
        if(null == accountIds || accountIds.isEmpty()){
            return null;
        }

        return this.baseMapper.selectRoleByAccountIds(accountIds);
    }

}
