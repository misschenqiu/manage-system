package com.starda.managesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starda.managesystem.config.Result;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.SysRoleMenu;
import com.starda.managesystem.pojo.SysUserRole;
import com.starda.managesystem.pojo.dto.RoleListDTO;
import com.starda.managesystem.pojo.po.CommonUpdateIdPO;
import com.starda.managesystem.pojo.po.role.RoleInsertPO;
import com.starda.managesystem.pojo.po.role.RoleSelectPO;
import com.starda.managesystem.pojo.vo.role.MenuRoleInfoVO;
import com.starda.managesystem.pojo.vo.role.RoleListVO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: ISysRoleService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/27 0:32
 * @Version: 1.0
 */
public interface ISysRoleService {

    /**
     * 创建角色
     * @param user
     * @param po
     * @throws Exception
     */
    void insertRole(UserVO user, RoleInsertPO po) throws Exception;

    /**
     * 修改角色信息
     * @param user
     * @param po
     * @throws Exception
     */
    void updateRoleInfo(UserVO user, RoleInsertPO po) throws Exception;

    /**
     * 删除角色
     * @param roleId
     * @throws Exception
     */
    void deleteRole(List<Integer> roleId) throws Exception;

    /**
     * 获取角色列表
     * @param po
     * @param userVO 用户信息
     * @return
     * @throws Exception
     */
    Result<RoleListVO> selectRoleList(UserVO userVO, RoleSelectPO po) throws Exception;

    /**
     * 批量添加角色信息
     * @param roleMenuList
     * @throws Exception
     */
    void insertSysRoleMenuList(List<SysRoleMenu> roleMenuList) throws Exception;

    /**
     * 删除中间表数据
     * @param roleId
     * @throws Exception
     */
    void deleteRoleMenuByRoleId(Integer roleId) throws Exception;

    /**
     * 批量添加角色信息
     * @param userRoles
     * @throws Exception
     */
    void insertRoleUser(List<SysUserRole> userRoles) throws Exception;

    /**
     * 删除账号角色信息
     * @param accountId
     * @throws Exception
     */
    void removeRoleUserByAccountIds(Integer accountId) throws Exception;

    /**
     * 添加用户 角色信息
     */
    void insertRoleUserList(Integer accountId, List<Integer> roleIds) throws Exception;

    /**
     * 获取角色信息
     * @param accountId
     * @return
     */
    List<RoleListDTO> getRoleList(Integer accountId) throws Exception;

    /**
     * 获取角色信息
     * @param accountIds
     * @return
     */
    List<RoleListDTO> getRoleByAccountIdsList(List<Integer> accountIds) throws Exception;

    /**
     * 详情
     * @param user
     * @param po
     * @return
     * @throws Exception
     */
    MenuRoleInfoVO getRoleInfo(UserVO user, CommonUpdateIdPO po) throws Exception;

}
