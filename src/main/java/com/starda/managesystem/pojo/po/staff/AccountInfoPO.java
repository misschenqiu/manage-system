package com.starda.managesystem.pojo.po.staff;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.staff
 * @ClassName: AccountInfoPO
 * @Author: chenqiu
 * @Description: 账号信息
 * @Date: 2021/8/28 15:30
 * @Version: 1.0
 */

@Data
public class AccountInfoPO {

    /**
     * 账号id
     */
    @NotNull(message = "账号id不能为空")
    private Integer accountId;

    /**
     * 归属地
     */
    private String address;

    /**
     * 编码
     */
    private String addressCode;

    /**
     * 角色id
     */
    private List<Integer> roleIds;

}
