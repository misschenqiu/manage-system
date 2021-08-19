package com.starda.managesystem.config;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config
 * @ClassName: ExceptionEnums
 * @Author: chenqiu
 * @Description: 异常管理枚举
 * @Date: 2021/8/19 18:26
 * @Version: 1.0
 */
public enum ExceptionEnums {
    SUCCESS("200", "success"),
    DEFAULT("100", "系统故障")
    ;

    private String code;

    private String message;

    ExceptionEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
