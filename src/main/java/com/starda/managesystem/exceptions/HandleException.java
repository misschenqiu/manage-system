package com.starda.managesystem.exceptions;

import com.starda.managesystem.config.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.exceptions
 * @ClassName: HandleException
 * @Author: chenqiu
 * @Description: 异常处理类
 * @Date: 2021/8/5 14:48
 * @Version: 1.0
 */

@ControllerAdvice
public class HandleException {

    /**
     * 处理自定义 异常
     * @return
     */
    @ExceptionHandler(ManageStarException.class)
    public Result paramException(){
        return new Result();
    }
}
