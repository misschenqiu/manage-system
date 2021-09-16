package com.starda.managesystem.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po
 * @ClassName: CommonParamPO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/16 23:57
 * @Version: 1.0
 */

@Data
public class CommonParamPO {

    @NotBlank(message = "序列号不能为空")
    private String param;

}
