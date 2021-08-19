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
public class ManageStarException extends RuntimeException{

    /**
     * 编码
     */
    private String code;

    /**
     * 错误
     */
    private String message;

}
