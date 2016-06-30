package com.tomasky.cache.redis;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.tomasky.cache.redis.container.RedisCacheContainer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 缓存服务引导实现.
 *
 * @author: yutaoxun
 * @date: 2016/6/3
 */
public class RedisCacheMain {
    private static final Logger LOG = LoggerFactory.getLogger(SpringContainer.class);

    private static volatile boolean running = true;

    public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";

    /**
     * 入口.
     *
     * @param args
     */
    public static void main(String[] args) {
        RedisCacheContainer container = new RedisCacheContainer();
        //停止
        stop(container);
        //启动
        start(container);
    }

    /**
     * 调用启动接口.
     */
    private static void start(RedisCacheContainer container) {
        //启动服务.
        container.start();
        LOG.info("Dubbo " + container.getClass().getSimpleName() + " started!");
        System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");
        synchronized (RedisCacheMain.class) {
            while (running) {
                try {
                    RedisCacheMain.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }

    /**
     * 调用停止接口.
     */
    private static void stop(final RedisCacheContainer container) {
        String shutdown_hook = System.getProperty(SHUTDOWN_HOOK_KEY);

        //停止服务.
        if (null != shutdown_hook && "true".equals(shutdown_hook)) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {

                    try {
                        container.stop();
                        LOG.info("Dubbo " + container.getClass().getSimpleName() + " stopped!");
                    } catch (Throwable t) {
                        LOG.error(t.getMessage(), t);
                    }
                    synchronized (RedisCacheMain.class) {
                        running = false;
                        RedisCacheMain.class.notify();
                    }
                }

            });
        }
    }
}
