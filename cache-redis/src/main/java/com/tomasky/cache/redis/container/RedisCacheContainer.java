package com.tomasky.cache.redis.container;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.container.Container;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: yutaoxun
 * @date: 2016/6/3
 */
public class RedisCacheContainer implements Container {

    private static final Logger LOG = LoggerFactory.getLogger(RedisCacheContainer.class);

    static final String[] DEFAULT_SPRING_CONFIG = new String[]{"classpath:META-INF/spring/dubbo/redis-cache-provider.xml", "classpath:META-INF/spring/applicationContext.xml"};

    private ClassPathXmlApplicationContext context;


    /**
     * 启动缓存服务.
     */
    @Override
    public void start() {
        context = new ClassPathXmlApplicationContext(DEFAULT_SPRING_CONFIG);
        context.start();
    }

    /**
     * 停止缓存服务.
     */
    @Override
    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
            }
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
