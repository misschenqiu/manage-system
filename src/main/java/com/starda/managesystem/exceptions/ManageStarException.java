package com.starda.managesystem.exceptions;

import lombok.Data;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.exceptions
 * @ClassName: ManageStarException
 * @Author: chenqiu
 * @Description: 管理系统异常管理
 * @Date: 2021/7/29 19:38
 * @Version: 1.0
 */

@Data
public class ManageStarException extends RuntimeException {

    /**
     * 编码
     */
    private String code;

    /**
     * 错误
     */
    private String message;


    /**
     * 默认异常，状态码为2
     *
     * @param msg
     */
    public ManageStarException(String msg) {
        super();
        this.message = msg;
        this.code = "100";
    }


    /**
     * 自定义异常
     *
     * @param code
     * @param massage
     */
    public ManageStarException(String code, String massage) {
        super(massage);
        this.message = message;
        this.code = code;
    }


    public ManageStarException() {
        super("系统错误，请联系管理员");
        this.code = "101";
        this.message = "系统错误，请联系管理员";
    }

}
