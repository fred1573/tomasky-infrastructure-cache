package com.tomasky.cache.api;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Cache.
 *
 * @author: yutaoxun
 * @date: 2016/6/1
 */
public interface Cache {

    /**
     * 获取一个Key．
     *
     * @param key
     * @return
     */
    Serializable get(String key);

    /**
     * 存放hash
     *
     * @param key       外层key
     * @param hashKey   内层hash key
     * @param hashValue hash value
     * @return
     */
    boolean putHash(String key, Object hashKey, Object hashValue);

    /**
     * 得到一个hashmap
     */
    Map<Object, Object> getHash(String key);

    /**
     * 得到hashmap中的一个value
     *
     * @param key
     * @param hashKey
     * @return
     */
    Object getHashValue(String key, Object hashKey);

    /**
     * 存放有序集合
     *
     * @param key   键
     * @param value 值
     * @param score 排序权重
     * @return
     */
    boolean putZSet(String key, Serializable value, double score);

    /**
     * 根据score范围得到有序集合
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<Serializable> getZSetByScore(String key, double min, double max);

    /**
     * 以秒为单位缓存．
     *
     * @param key
     * @param result
     * @param time
     * @return
     */
    boolean put(String key, Serializable result, long time);


    /**
     * 以指定的时间单位缓存．
     *
     * @param key
     * @param result
     * @param time
     * @param timeUnit
     * @return
     */
    boolean put(String key, Serializable result, long time, TimeUnit timeUnit);

    /**
     * 缓存时间,指定过期时间．
     *
     * @param key
     * @param result
     * @param idleDate
     * @return
     */
    boolean put(String key, Serializable result, Date idleDate);

    /**
     * 删除缓存．
     *
     * @param key
     * @return
     */
    boolean del(String... key);

    /**
     * 它以秒为单位返回 key 的剩余生存时间．
     *
     * @param key
     * @return
     */
    long timeToLive(String key);

    /**
     * 指定时间单位．
     *
     * @param key
     * @param timeUnit
     * @return
     */
    long timeToLive(String key, TimeUnit timeUnit);

    /**
     * key是否存在．
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 当且仅当 newkey 不存在时，将 key 改名为 newkey．
     *
     * @param oldKeyName
     * @param newKeyName
     * @return
     */
    boolean rename(String oldKeyName, String newKeyName);
}
