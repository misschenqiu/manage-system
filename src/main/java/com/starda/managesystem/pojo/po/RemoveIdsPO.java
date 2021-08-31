package com.starda.managesystem.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po
 * @ClassName: RemoveIdsPO
 * @Author: chenqiu
 * @Description:
 * @Date: 2021/8/31 21:01
 * @Version: 1.0
 */

@Data
public class RemoveIdsPO {

    /**
     * 删除对象
     */
    @NotNull(message = "请选择操作对象")
    private List<Integer> idList;

    /**
     * 操作类型
     * 1.开启 2.删除 3.重复提醒
     */
    @NotNull(message = "请选择操作类型")
    private Integer type;

    /**
     * 操作属性
     * 是否开启 0 否  1.是
     */
    private Integer reminderOpen;

    /**
     * 是否重复提醒
     * 是否重复提醒 0.否 1.是
     */
    private Integer again;

}
