package com.bing.boot.server.mq;

import com.bing.boot.api.User;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:MQ消息生产服务
 * Author: zhangfusheng
 * Date: 2018/9/27 下午3:27
 */
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue helloQueue;

    @Autowired
    private Queue userQueue;

    public void sendString() {
        String context = "hello " + new Date();
        System.err.println("Producer : " + context);
        rabbitTemplate.convertAndSend(helloQueue.getName(), context);
    }

    public void sendUser() {
//        User user = new User("张三", 20);
//        System.err.println("Producer : " + user);
        for (int i = 0; i < 10; i++) {
            // User类需要序列化 implements Serializable
            User user = new User("张三", i);
            rabbitTemplate.convertAndSend(userQueue.getName(), user);
        }
    }
}
