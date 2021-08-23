package com.starda.managesystem.common;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;

/**
 * @ProjectName: manage-system
 * @Package: com.starda.managesystem.common
 * @ClassName: LocalCache
 * @Author: chenqiu
 * @Description: 本地缓存信息
 * @Date: 2021/8/22 19:58
 * @Version: 1.0
 */
public class LocalCache {

    /**
     * 默认缓存时长 单位s
     */
    private static final Long DEFAULT_TIMEOUT = 12 * 60 * 60 * 1000L;
    /**
     * 默认清理间隔时间 单位s
     */
    private static final Long CLEAN_TIMEOUT = 12  * 60 * 60 * 1000L;
    /**
     * 缓存对象
     */
    public static TimedCache<String, Object> timedCache = CacheUtil.newTimedCache(DEFAULT_TIMEOUT);

    static {
        //启动定时任务
        timedCache.schedulePrune(CLEAN_TIMEOUT);
    }

    /**
     * 缓存数据
     *
     * @param key
     * @param value
     */
    public static void put(String key, String value) {
        timedCache.put(key, value);
    }

    /**
     * 缓存数据 设置缓存时间
     *
     * @param key
     * @param value
     * @param expire 单位分钟
     */
    public static void put(String key, String value, Long expire) {
        timedCache.put(key, value, DateUnit.SECOND.getMillis() * expire);
    }

    /**
     * 禁止延迟缓存 isUpdateLastAccess = false
     *
     * @param key
     * @param isUpdateLastAccess
     */
    public static Object get(String key, boolean isUpdateLastAccess) {
        return timedCache.get(key, isUpdateLastAccess);
    }

    /**
     * 获取值，并更新数据清理时间
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return timedCache.get(key);
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public static void remove(String key) {
        timedCache.remove(key);
    }

    /**
     * 清理缓存
     */
    public static void clear() {
        timedCache.clear();
    }

}
