package com.starda.managesystem.pojo.dto;

import com.starda.managesystem.pojo.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.dto
 * @ClassName: MnusToRoleInfoDTO
 * @Author: chenqiu
 * @Description: 路径角色 实例
 * @Date: 2021/8/22 22:39
 * @Version: 1.0
 */

@Data
public class MenusToRoleInfoDTO implements Serializable {

    private static final long serializableUUid = 1L;

    private Integer id;

    /**
     * 菜单名
     */
    private String menu_name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 路劲
     */
    private String url;

    /**
     * 角色列表
     */
    private List<RoleInfoListDTO> roleList;

}
