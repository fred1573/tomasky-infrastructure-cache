package com.tomasky.cache.redis;

import com.tomasky.cache.api.Cache;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: yutaoxun
 * @date: 2016/6/1
 */
public class RedisCacheTest {

    private static ClassPathXmlApplicationContext context;
    private Cache cache;

    static {
        context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/dubbo/redis-cache-consumer.xml");
        context.start();
    }

    private Cache initCache() {
        return (Cache) context.getBean("cache");
    }

    /**
     * 测试boolean put(String key, Serializable result, long time)接口
     */
    @Test
    public void putTestWithTimeLong() {
        Cache cache = initCache();
        for (int i = 0; i < 50000; i++) {
            cache.put("d" + i, "589789544222222", 1800);
        }
        context.close();
    }

    @Test
    public void putTestWithTimeUnit() {
        Cache cache = initCache();
        cache.put("omsNo", "589789544222222", 10000, TimeUnit.SECONDS);
        context.close();
    }

    @Test
    public void getTest() {
        Cache cache = initCache();
        String value = (String) cache.get("otaNo");
        Assert.assertEquals("测试一下", "589789544222222", value);
        context.close();
    }

    @Test
    public void delTest() throws IOException {
        Cache cache = initCache();
        if (cache.del("omsNo1")) {
            System.out.println("delete success");
        }
        context.close();
    }

    @Test
    public void putHashTest() throws IOException {
        Cache cache = initCache();
        cache.putHash("999", "777", 777);
        context.close();
    }

    @Test
    public void getHashTest() throws IOException {
        Cache cache = initCache();
        Map<Object, Object> hash = cache.getHash("999");
        System.out.println(hash);
        context.close();
    }

    @Test
    public void getHashValueTest() throws IOException {
        Cache cache = initCache();
        Object hash = cache.getHashValue("999", "666");
        System.out.println(hash);
        context.close();
    }

    @Test
    public void putZSetTest() throws IOException {
        Cache cache = initCache();
        if (cache.putZSet("555", 123, 9)) {
            System.out.println("success");
        }
        context.close();
    }
}
