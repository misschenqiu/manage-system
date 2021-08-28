package com.starda.managesystem.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.starda.managesystem.pojo.SysRoleMenu;
import com.starda.managesystem.pojo.dto.MenuAddressDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper extends MPJBaseMapper<SysRoleMenu> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    /**
     * 获取到权限信息列表
     */
    List<MenuAddressDTO> getMenuAddressList(List<Integer> roleList) throws Exception;

    /**
     * 获取全部权限
     * @return
     * @throws Exception
     */
    List<MenuAddressDTO> getMenuAddressListAll() throws Exception;

    /**
     * 批量添加角色权限信息
     * @param sysRoleMenus
     * @throws Exception
     */
    void insertSysRoleMenuList(@Param("roleMenuList") List<SysRoleMenu> sysRoleMenus) throws Exception;

}