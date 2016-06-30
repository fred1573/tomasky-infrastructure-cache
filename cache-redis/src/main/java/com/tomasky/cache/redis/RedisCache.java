package com.tomasky.cache.redis;

import com.tomasky.cache.api.Cache;
import com.tomasky.cache.redis.support.SerializableRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存实现．
 *
 * @author: yutaoxun
 * @date: 2016/6/1
 */
@Component
public class RedisCache implements Cache {
    @Autowired
    private SerializableRedisTemplate serializableRedisTemplate;

    @Override
    public Serializable get(String key) {
        BoundValueOperations<String, Serializable> boundValueOperations = serializableRedisTemplate
                .boundValueOps(key);
        return boundValueOperations.get();
    }

    @Override
    public boolean putHash(String key, Object hashKey, Object value) {
        serializableRedisTemplate.boundHashOps(key).put(hashKey, value);
        return true;
    }

    @Override
    public Map<Object, Object> getHash(String key) {
        return serializableRedisTemplate.boundHashOps(key).entries();
    }

    @Override
    public Object getHashValue(String key, Object hashKey) {
        return serializableRedisTemplate.boundHashOps(key).get(hashKey);
    }

    @Override
    public boolean putZSet(String key, Serializable value, double score) {
        serializableRedisTemplate.boundZSetOps(key).add(value, score);
        return true;
    }

    @Override
    public Set<Serializable> getZSetByScore(String key, double min, double max) {
        return serializableRedisTemplate.boundZSetOps(key).rangeByScore(min, max);
    }

    @Override
    public boolean put(String key, Serializable result, long timeToIdleMs) {
        return this.put(key, result, timeToIdleMs, TimeUnit.SECONDS);
    }

    @Override
    public boolean put(String key, Serializable result, long time,
                       TimeUnit timeUnit) {
        BoundValueOperations<String, Serializable> boundValueOperations = serializableRedisTemplate
                .boundValueOps(key);
        boundValueOperations.set(result);
        serializableRedisTemplate.expire(key, time, timeUnit);
        return true;
    }

    @Override
    public boolean put(String key, Serializable result, Date idleDate) {
        BoundValueOperations<String, Serializable> boundValueOperations = serializableRedisTemplate
                .boundValueOps(key);
        boundValueOperations.set(result);
        serializableRedisTemplate.expireAt(key, idleDate);
        return true;
    }

    @Override
    public boolean del(String... key) {
        serializableRedisTemplate.delete(Arrays.asList(key));
        return true;
    }

    @Override
    public boolean exists(String key) {
        return serializableRedisTemplate.hasKey(key);
    }

    @Override
    public boolean rename(String oldKey, String newKey) {
        return serializableRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public long timeToLive(String key) {
        return this.timeToLive(key, TimeUnit.SECONDS);
    }

    @Override
    public long timeToLive(String key, TimeUnit timeUnit) {
        return serializableRedisTemplate.getExpire(key, timeUnit);
    }

    public void setSerializableRedisTemplate(SerializableRedisTemplate serializableRedisTemplate) {
        this.serializableRedisTemplate = serializableRedisTemplate;
    }
}
