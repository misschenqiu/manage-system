package com.starda.managesystem.config;

import com.starda.managesystem.constant.Constant;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.config
 * @ClassName: Resutl
 * @Author: chenqiu
 * @Description: 返回类型定义
 * @Date: 2021/8/5 15:01
 * @Version: 1.0
 */

@Data
public class Result<T> {

    public Result(){
    }

    /**
     * 直接成功
     */
    public static DefaultResult success(){
       return new DefaultResult();
    }

    /**
     * 成功后添加说明
     */
    public static DefaultResult success(Object data, String message){
       return new DefaultResult(data, message);
    }

    /**
     * 成功值返回说明
     */
    public static DefaultResult success(String message){
        return new DefaultResult(message);
    }


    /**
     * 返回数据 带分页数据
     * @param data
     */
    public ResultPage resultPage(List<T> data, Long currentPage, Long pageSize){
        return new ResultPage(data, currentPage, pageSize);
    }

    /**
     * 返回数据 不带分页
     * @param
     */
    public DefaultResult resultPage(T data){
        return new DefaultResult(data);
    }

    /**
     * 返回错误信息
     * @param code
     * @param message
     */
    public static Error error(String code, String message, String msg){
        return new Error(code, message, msg);
    }

    /**
     * 返回错误信息 自带异常
     * @param msg
     */
    public static Error error(String msg){
        return new Error(msg);
    }

}

/**
 * 默认返回类
 * @param <T>
 */
@Data
class DefaultResult<T> extends Result {
    /**
     * 对应编码
     */
    private String code;

    /**
     * 返回数据
     */
    private Object data;


    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误编码
     */
    private Boolean success;

    /**
     * 直接成功
     */
    public DefaultResult(){
        this.code = ExceptionEnums.SUCCESS.getCode();
        this.message = Constant.ResultCodeMessage.SUCCESS;
        this.success = Constant.ResultCodeMessage.DEFAULT;
    }

    /**
     * 只返回说明
     */
    public DefaultResult(String message){
        this.code = ExceptionEnums.SUCCESS.getCode();
        this.success = Constant.ResultCodeMessage.DEFAULT;
        this.message = message;
    }

    /**
     * 成功后添加说明
     */
    public DefaultResult(T data, String message){
        this.code = ExceptionEnums.SUCCESS.getCode();
        this.success = Constant.ResultCodeMessage.DEFAULT;
        this.data = data;
        this.message = message;
    }

    /**
     * 不带分页数据
     */
    public DefaultResult(T data){
        this.code = ExceptionEnums.SUCCESS.getCode();
        this.message = Constant.ResultCodeMessage.SUCCESS;
        this.success = Constant.ResultCodeMessage.DEFAULT;
        this.data = data;
    }

}


/**
 * 异常返回类
 */
@Data
class Error extends Result<T>{
    /**
     * 对应编码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 异常详情
     */
    private String msg;

    /**
     * 错误编码
     */
    private Boolean success;

    /**
     * 返回错误信息
     * @param code
     * @param message
     */
    public Error(String code, String message, String msg){
        this.code = code;
        this.message = message;
        this.success = Constant.ResultCodeMessage.ERROR;
        this.msg = msg;
    }

    /**
     * 返回错误信息 自带异常
     * @param msg
     */
    public Error(String msg){
        this.code = ExceptionEnums.DEFAULT.getCode();
        this.message = ExceptionEnums.DEFAULT.getMessage();
        this.success = Constant.ResultCodeMessage.ERROR;
        this.msg = msg;
    }

    public Error(){
    }

}

/**
 * 带分页数据
 */
@Data
class ResultPage<T> extends Result<T>{
    /**
     * 对应编码
     */
    private String code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误编码
     */
    private Boolean success;

    /**
     * 显示条数
     */
    private Long currentPage;

    /**
     * 显示页码
     */
    private Long pageSize;

    /**
     * 返回带参数
     * @param data 数据
     * @param currentPage 显示条数
     * @param pageSize 当前页
     */
    public ResultPage (List<T> data, Long currentPage, Long pageSize){
        this.code = ExceptionEnums.SUCCESS.getCode();
        this.message = Constant.ResultCodeMessage.SUCCESS;
        this.success = Constant.ResultCodeMessage.DEFAULT;
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

}