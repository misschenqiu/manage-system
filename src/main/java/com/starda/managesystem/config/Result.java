package com.starda.managesystem.config;

import com.starda.managesystem.constant.Constant;
import lombok.Data;

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

    /**
     * 对应编码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 显示页码
     */
    private Integer currentPage;

    /**
     * 显示条数
     */
    private Integer pageSize;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误编码
     */
    private Boolean success;

    /**
     * 用户认证
     */
    private String token;

    /**
     * 直接成功
     */
    public Result(){
        this.code = 200;
        this.message = Constant.ResultCodeMessage.SUCCESS;
        this.success = Constant.ResultCodeMessage.DEFAULT;
    }
    /**
     * 成功后添加说明
     */
    public Result(String message){
        this.code = 200;
        this.message = message;
        this.success = Constant.ResultCodeMessage.DEFAULT;
    }


    /**
     * 返回数据 带分页数据
     * @param data
     */
    public Result(List<T> data, Integer currentPage, Integer pageSize){
        new Result();
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * 返回数据 不带分页
     * @param data
     */
    public Result(T data){
        new Result();
        this.data = data;
    }

    /**
     * 返回错误信息
     * @param code
     * @param message
     */
    public Result(Integer code, String message){
        this.code = code;
        this.message = message;
        this.success = Constant.ResultCodeMessage.ERROR;
    }

    /**
     * 登录验证数据
     * @param token
     * @param code
     */
    public Result(String token, Integer code){
        new Result();
        this.token = token;
        this.code = code;
    }

}
