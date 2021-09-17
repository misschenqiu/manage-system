package com.starda.managesystem.pojo.po.app;

import com.starda.managesystem.pojo.po.business.QueryBusinessInfoPO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po.app
 * @ClassName: AppManageQueryBusinessPO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/17 12:56
 * @Version: 1.0
 */

@Data
public class AppManageQueryBusinessPO extends QueryBusinessInfoPO {

    @NotNull(message = "查看类型1.待完成 2.已完成")
    private Integer type;

}
