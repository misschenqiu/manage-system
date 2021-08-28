package com.starda.managesystem.mapper.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysRole;
import com.starda.managesystem.pojo.dto.RoleListDTO;
import com.starda.managesystem.pojo.dto.role.RoleDTO;
import com.starda.managesystem.pojo.po.role.RoleSelectPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends MPJBaseMapper<SysRole> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 批量修改role 状态
     * @param roleList
     * @throws Exception
     */
    void updateRoleListById(@Param("roleList") List<Integer> roleList) throws Exception;

    /**
     * 查询账号角色
     * @param accountId
     * @return
     * @throws Exception
     */
    List<RoleListDTO> selectRoleByAccountId(Integer accountId) throws Exception;

    /**
     * 查询账号角色
     * @param accountIds
     * @return
     * @throws Exception
     */
    List<RoleListDTO> selectRoleByAccountIds(@Param("accountIds") List<Integer> accountIds) throws Exception;

    /**
     * 查询角色列表
     * @param page
     * @param po
     * @return
     * @throws Exception
     */
    IPage<RoleDTO> selectRolePage(Page<RoleDTO> page, @Param("role") RoleSelectPO po) throws Exception;

}