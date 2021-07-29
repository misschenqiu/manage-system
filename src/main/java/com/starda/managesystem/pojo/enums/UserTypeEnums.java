package com.starda.managesystem.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.pojo.enums
 * @ClassName: UserTypeEnums
 * @Author: chenqiu
 * @Description: 用户性别枚举
 * @Date: 2021/7/29 10:57
 * @Version: 1.0
 */
public enum UserTypeEnums {

    MAN(1, "男"),

    WOMAN(2, "女");

    /**
     * 标记数据库 字段
     */
    @EnumValue
    @JsonValue //标记响应json值
    private final int status;

    private String message;

    UserTypeEnums(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
