package com.starda.managesystem.exceptions;

import cn.hutool.core.util.StrUtil;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public Result systemManageException(ManageStarException e){
        if(e.getCode() == null || StrUtil.isBlank(e.getCode())){
            e.setCode(ExceptionEnums.DEFAULT.getCode());
        }
        return Result.error(e.getCode(), e.getMessage(), e.toString());
    }

    /**
     * 系统自定义 异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result bootException(Exception e){
        return Result.error(e.getMessage());
    }

}
