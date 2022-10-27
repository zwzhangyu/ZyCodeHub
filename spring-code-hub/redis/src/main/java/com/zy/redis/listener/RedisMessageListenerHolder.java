package com.zy.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 *  redis订阅注册
 * @author zhangyu
 * @date 2022/10/27
 **/
@Configuration
public class RedisMessageListenerHolder {

    @Autowired
    private RedisTemplate redisTemplate = null;

    // Redis 连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    // Redis 消息监听器
    @Autowired
    private MessageListener redisMsgListener = null;

    // 任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    /**
     * 创建任务池，运行线程等待处理 Redis 的消息
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义 Redis 的监听容器
     *
     * @return 监听容器
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        // Redis 连接工厂
        container.setConnectionFactory(connectionFactory);
        // 设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        // 定义监听渠道，名称为 topic1
        Topic topic = new ChannelTopic(RedisMessageConstant.TOPIC);
        // 使用监听器监听 Redis 的消息
        container.addMessageListener(redisMsgListener, topic);
        return container;
    }
}
