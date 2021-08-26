package com.starda.managesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.mapper.system.SysRoleMapper;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.po.role.RoleInsertPO;
import com.starda.managesystem.pojo.po.role.RoleSelectPO;
import com.starda.managesystem.pojo.vo.role.RoleListVO;
import com.starda.managesystem.service.ISysRoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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


    @Override
    public void insertRole(UserVO user, RoleInsertPO po) throws Exception {

    }

    @Override
    public void deleteRole(List<Integer> roleId) throws Exception {

    }

    @Override
    public List<RoleListVO> selectRoleList(UserVO userVO, RoleSelectPO po) throws Exception {
        return null;
    }
}
