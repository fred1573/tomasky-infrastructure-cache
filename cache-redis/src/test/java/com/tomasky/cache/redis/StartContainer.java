package com.tomasky.cache.redis;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: yutaoxun
 * @date: 2016/6/1
 */
public class StartContainer {

    /**
     * 测试boolean put(String key, Serializable result, long time)接口
     */
    @Test
    public void start() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/dubbo/redis-cache-provider.xml", "classpath:META-INF/spring/applicationContext.xml");
        context.start();
        System.in.read();
    }
}
