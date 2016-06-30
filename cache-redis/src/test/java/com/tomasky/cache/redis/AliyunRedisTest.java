package com.tomasky.cache.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 主要的目的是测试一下阿里云redis服务是否可用.
 * @author: yutaoxun
 * @date: 2016/6/1
 */
public class AliyunRedisTest {

    @Test
    public void setTest(){
        try {
            String host = "115.28.185.117";//控制台显示访问地址
            int port = 6379;
            Jedis jedis = new Jedis(host, port);
            //鉴权信息由用户名:密码拼接而成
            jedis.auth("1ecd084e0048468d:91Doxhgnp6aL1pipwewyicug3ujnxe");//instance_id:password
            String key = "redis";
            String value = "aliyun-redis";
            //select db默认为0
            jedis.select(1);
            //set一个key
            jedis.set(key, value);
            System.out.println("Set Key " + key + " Value: " + value);
            //get 设置进去的key
            String getvalue = jedis.get(key);
            System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
            jedis.quit();
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
