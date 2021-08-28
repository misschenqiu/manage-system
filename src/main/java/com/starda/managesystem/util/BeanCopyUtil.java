package com.starda.managesystem.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.util
 * @ClassName: BeantranUtil
 * @Author: chenqiu
 * @Description: 并操作类
 * @Date: 2021/8/28 11:06
 * @Version: 1.0
 */
@Log4j2
public class BeanCopyUtil {

    /**
     * copy 实例
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(Object source, Class<T> clazz, HashMap<String, String> mapping){
        T target = BeanUtil.toBean(source, clazz, CopyOptions.create().setFieldMapping(mapping));
        return target;
    }

    /**
     * copy 集合
     * @param collection
     * @param targetType
     * @param mapping
     * @param <T>
     * @return
     */
    public static <T> List<T> copyToList(Collection<?> collection, Class<T> targetType, HashMap<String, String> mapping){
        List<T> beanList = BeanUtil.copyToList(collection, targetType, CopyOptions.create().setFieldMapping(mapping));
        return beanList;
    }

}
