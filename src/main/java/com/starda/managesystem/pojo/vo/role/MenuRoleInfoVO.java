package com.starda.managesystem.pojo.vo.role;

import com.starda.managesystem.pojo.vo.MenuAddressVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.role
 * @ClassName: MenuRoleInfoVO
 * @Author: chenqiu
 * @Description: 角色详情
 * @Date: 2021/9/20 22:12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRoleInfoVO {

    /**
     * 角色详情
     */
    List<MenuAddressVO> addressVOList;

    /**
     * 单纯id
     */
    List<Integer> menuIds;
}
