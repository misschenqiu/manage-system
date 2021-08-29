package com.starda.managesystem.pojo.vo.staff;

import com.starda.managesystem.pojo.vo.role.RoleListVO;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.staff
 * @ClassName: AccountInfoVO
 * @Author: chenqiu
 * @Description: 账号信息
 * @Date: 2021/8/29 18:44
 * @Version: 1.0
 */

@Data
public class AccountInfoVO {

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
     * 地区编码
     */
    private String addressCode;

    /**
     * 描述
     */
    private String remark;

    /**
     * 角色列表
     */
    private List<RoleListVO> roleListVOList;

}
