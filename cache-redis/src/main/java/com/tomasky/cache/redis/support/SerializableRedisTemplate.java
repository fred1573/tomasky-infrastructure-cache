package com.tomasky.cache.redis.support;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author: yutaoxun
 * @date: 2016/6/1
 */
public class SerializableRedisTemplate extends RedisTemplate<String, Serializable> {

    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();

    public SerializableRedisTemplate() {
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
        setKeySerializer(STRING_SERIALIZER);
        setValueSerializer(serializer);
        setHashKeySerializer(STRING_SERIALIZER);
        setHashValueSerializer(serializer);
    }

    public SerializableRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        this();
        setConnectionFactory(redisConnectionFactory);
        afterPropertiesSet();
    }

    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}