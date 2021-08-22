package com.starda.managesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.mapper.system.SysMenuMapper;
import com.starda.managesystem.mapper.system.SysRoleMapper;
import com.starda.managesystem.mapper.system.SysRoleMenuMapper;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.SysRoleMenu;
import com.starda.managesystem.pojo.dto.RoleInfoListDTO;
import com.starda.managesystem.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.service.impl
 * @ClassName: SysMenuServiceimpl
 * @Author: chenqiu
 * @Description: 角色信息查询
 * @Date: 2021/8/20 17:58
 * @Version: 1.0
 */

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<SysMenu> getMenuInfoList(List<Integer> accountId) {
        MPJLambdaWrapper<SysMenu> wrapper = new MPJLambdaWrapper<>();
        return this.list(wrapper.selectAll(SysMenu.class)
                .rightJoin(SysRoleMenu.class, SysRoleMenu::getMenu_id, SysMenu::getId)
                .leftJoin(SysRole.class, SysRole::getId, SysRoleMenu::getRole_id)
                .in(SysRole::getId, accountId));
    }

    @Override
    public List<SysMenu> getMenuAllToRoleList() {
        return this.list();
    }

    @Override
    public List<RoleInfoListDTO> getRoleToAllMenusList() {
        // 获取全部角色信息
        List<SysRole> roleList = this.roleMapper.selectList(null);

        // 获取全部 角色 资源 中间表
        List<SysRoleMenu> roleMenuList = this.roleMenuMapper.selectList(null);

        // 整理数据
        List<RoleInfoListDTO> roleInfoListDTOS = BeanUtil.copyToList(roleList, RoleInfoListDTO.class);
        roleInfoListDTOS.stream().forEach(role -> {
            // 整理 角色对应 资源
            List<Integer> menus = new ArrayList<>();
            roleMenuList.stream().forEach(roleMenu -> {
                if(role.getId().equals(roleMenu.getRole_id())){
                    menus.add(roleMenu.getMenu_id());
                }
            });

            role.setMenuIds(menus);
        });

        return roleInfoListDTOS;
    }
}
