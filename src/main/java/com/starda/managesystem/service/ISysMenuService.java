package com.starda.managesystem.service;

import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.dto.RoleInfoListDTO;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service
 * @ClassName: ISysMenuService
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/20 17:57
 * @Version: 1.0
 */
public interface ISysMenuService {

    /**
     * 获取所有权限信息
     * @param accountId 角色集合
     * @return
     */
    List<SysMenu> getMenuInfoList(List<Integer> accountId);

    /**
     * 获取全部 路径信息
     * @return
     */
    List<SysMenu> getMenuAllToRoleList();

    /**
     * 获取 角色 列表信息
     * @return
     */
    List<RoleInfoListDTO> getRoleToAllMenusList();

}
