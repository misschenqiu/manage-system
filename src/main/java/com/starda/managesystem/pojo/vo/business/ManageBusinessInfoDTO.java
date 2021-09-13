package com.starda.managesystem.pojo.vo.business;

import com.starda.managesystem.pojo.ManageBusinessInfo;
import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.vo.business
 * @ClassName: ManageBusinessInfoDTO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/9/13 22:18
 * @Version: 1.0
 */

@Data
public class ManageBusinessInfoDTO extends ManageBusinessInfo {

    /**
     * 员工 头像
     */
    private String headImg;

}
