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
    /* 默认信息 */
    SUCCESS("200", "success"),
    DEFAULT("100", "系统故障"),
    PARAMS("101", "参数异样"),
    CANCELLATION("200", "注销成功"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID("1001", "参数无效"),
    PARAM_IS_BLANK("1002", "参数为空"),
    PARAM_TYPE_ERROR("1003", "参数类型错误"),
    PARAM_NOT_COMPLETE("1004", "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN("2001", "用户未登录"),
    USER_ACCOUNT_EXPIRED("2002", "账号登录已过期"),
    USER_CREDENTIALS_ERROR("2003", "密码错误"),
    USER_CREDENTIALS_EXPIRED("2004", "密码过期"),
    USER_ACCOUNT_DISABLE("2005", "账号不可用"),
    USER_ACCOUNT_LOCKED("2006", "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST("2007", "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST("2008", "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS("2009", "账号在异处登录"),
    USER_ACCOUNT_NOT_STATUS("2010","禁用操作"),
    USER_ACCOUNT_DELETE_EXIST("2011","删除失败"),
    USER_ACCOUNT_UPDATE_ID("2012","修改失败"),
    USER_ACCOUNT_CANCELLATION("2013","注销失败"),
    USER_ACCOUNT_AGAIN_PASSWORD("2014","两次密码不对"),

    /* 业务错误 */
    NO_PERMISSION("3001", "没有权限"),
    NO_FILE_NULL("3002", "文件不能为空"),
    FILE_EXIST("3003", "文件已存在"),
    FILE_LOAD_FAIL("3004", "文件上传失败"),
    ADDRESS_URL("3005", "前端路径与父级菜单不能匹配"),
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
