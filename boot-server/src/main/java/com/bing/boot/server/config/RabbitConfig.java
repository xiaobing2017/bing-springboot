package com.bing.boot.server.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2018/9/27 下午3:18
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("queue.bing.hello");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("queue.bing.user");
    }
}
