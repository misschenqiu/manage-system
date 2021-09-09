package com.starda.managesystem.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po
 * @ClassName: CommonUpdateIdPO
 * @Author: chenqiu
 * @Description: 公共单个id操作
 * @Date: 2021/9/9 21:43
 * @Version: 1.0
 */

@Data
public class CommonUpdateIdPO {

    /**
     * id
     */
    @NotNull(message = "请选择操作对象")
    private Integer id;

}
