package com.starda.managesystem.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.po
 * @ClassName: CommonIdsPO
 * @Author: chenqiu
 * @Description: 存放id
 * @Date: 2021/9/4 13:29
 * @Version: 1.0
 */
@Data
public class CommonIdsPO {

    /**
     * id集合
     */
    @NotNull(message = "请选择删除对象")
    private List<Integer> ids;

    /**
     * 是否确认数据 1.确认
     */
    private Integer type;

}
