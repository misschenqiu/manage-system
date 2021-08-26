package com.starda.managesystem.service;

import com.starda.managesystem.config.author.UserVO;
import com.starda.managesystem.pojo.po.role.RoleInsertPO;
import com.starda.managesystem.pojo.po.role.RoleSelectPO;
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
     * 删除角色
     * @param roleId
     * @throws Exception
     */
    void deleteRole(List<Integer> roleId) throws Exception;

    /**
     * 获取角色列表
     * @param po
     * @return
     * @throws Exception
     */
    List<RoleListVO> selectRoleList(UserVO userVO, RoleSelectPO po) throws Exception;

}
