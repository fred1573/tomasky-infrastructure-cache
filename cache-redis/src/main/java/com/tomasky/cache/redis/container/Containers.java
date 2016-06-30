package com.tomasky.cache.redis.container;

/**
 * 帮助方法.
 * @author: yutaoxun
 * @date: 2016/6/8
 */
public final  class Containers {
    /**
     * 构建容器.
     * @return 容器实例.
     */
    public static RedisCacheContainer container(){
        return new RedisCacheContainer();
    }
}
