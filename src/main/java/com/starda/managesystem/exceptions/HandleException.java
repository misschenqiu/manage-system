package com.starda.managesystem.exceptions;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.starda.managesystem.config.ExceptionEnums;
import com.starda.managesystem.config.Result;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;
import java.util.stream.Collectors;

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
@Log4j2
public class HandleException {

    /**
     * 处理自定义 异常
     *
     * @return
     */
    @ExceptionHandler(ManageStarException.class)
    @ResponseBody
    public Result systemManageException(ManageStarException e) {
        log.info("只定义异常处理" + e.getMessage());
        if (e.getCode() == null || StrUtil.isBlank(e.getCode())) {
            e.setCode(ExceptionEnums.DEFAULT.getCode());
        }
        return Result.error(e.getCode(), e.getMessage(), e.toString());
    }

    /**
     * 处理所有校验失败的异常（MethodArgumentNotValidException异常）
     * 设置响应状态码为400
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleBindGetException(MethodArgumentNotValidException ex) {
        // 获取所有异常
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        return Result.error(ExceptionEnums.PARAMS.getCode(), ExceptionEnums.PARAMS.getMessage(), String.join(",", errors));
    }

    /**
     * 处理所有参数校验时抛出的异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleBindException(ValidationException ex) {
        // 获取所有异常
        List<String> errors = new LinkedList<String>();
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                errors.add(item.getMessage());
            }
        }
        return Result.error(String.join(",", errors));
    }

    /**
     * Controller参数绑定错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("参数异常信息：" + ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 参数注解 拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindParamException(BindException e) {
        Map<String, String> errorList = new HashMap<String, String>();
        for (FieldError allError : e.getBindingResult().getFieldErrors()) {
            errorList.put(allError.getField(), allError.getDefaultMessage());
        }
        return Result.error(ExceptionEnums.PARAMS.getCode(), ExceptionEnums.PARAMS.getMessage(), JSON.toJSONString(errorList));
    }

    /**
     * 系统自定义 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result bootException(Exception e) {
        log.error("全局错误" + e.getMessage());
        return Result.error(e.getMessage());
    }

}
