package com.bing.boot.server.mq;

import com.bing.boot.api.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:MQ消息消费服务
 * Author: zhangfusheng
 * Date: 2018/9/27 下午3:29
 */
@Component
public class Consumer {

    @RabbitListener(queues = {"queue.bing.hello"})
    public void processString(String context) {
        System.err.println("Consumer : " + context);
    }

//    @RabbitListener(queues = "queue.bing.user")
//    public void processUser(String context) {
//        User user = JSONObject.parseObject(context, User.class);
//        System.err.println("Consumer1 : " + user);
//    }

    @RabbitListener(queues = "queue.bing.user")
    public void processUser2(User user) {
        System.err.println("Consumer2 : " + user);
    }
}
