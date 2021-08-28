package com.starda.managesystem.pojo.vo.staff;

import com.starda.managesystem.pojo.vo.role.RoleListVO;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.staff
 * @ClassName: AccountInfoListVO
 * @Author: chenqiu
 * @Description: 账号信息列表
 * @Date: 2021/8/28 17:58
 * @Version: 1.0
 */

@Data
public class AccountInfoListVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 账号名
     */
    private String accountName;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 所属地
     */
    private String address;

    /**
     * 角色列表
     */
    private List<RoleListVO> roleListVOList;

}
