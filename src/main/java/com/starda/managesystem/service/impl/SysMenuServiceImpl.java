package com.starda.managesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starda.managesystem.mapper.system.SysMenuMapper;
import com.starda.managesystem.pojo.SysMenu;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.SysRoleMenu;
import com.starda.managesystem.service.ISysMenuService;
import org.springframework.stereotype.Service;

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


    @Override
    public List<SysMenu> getMenuInfoList(List<Integer> accountId) {
        MPJLambdaWrapper<SysMenu> wrapper = new MPJLambdaWrapper<>();
        return this.list(wrapper.selectAll(SysMenu.class)
                .rightJoin(SysRoleMenu.class, SysRoleMenu::getMenu_id, SysMenu::getId)
                .leftJoin(SysRole.class, SysRole::getId, SysRoleMenu::getRole_id)
                .in(SysRole::getId, accountId));
    }
}
