package com.starda.managesystem.pojo.vo.role;

import com.starda.managesystem.pojo.dto.MenuAddressDTO;
import com.starda.managesystem.pojo.vo.MenuAddressVO;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.role
 * @ClassName: MenuAddressListVO
 * @Author: chenqiu
 * @Description: 权限列表
 * @Date: 2021/8/29 11:58
 * @Version: 1.0
 */

@Data
public class MenuAddressListVO extends MenuAddressVO{

    /**
     * 后端路劲详情
     */
    private List<MenuAddressDTO> addressInfo;

}
