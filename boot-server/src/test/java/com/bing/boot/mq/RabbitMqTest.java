package com.bing.boot.mq;

import com.bing.boot.server.mq.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2018/9/27 下午3:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private Producer producer;

    @Test
    public void testString() throws Exception {
        producer.sendString();
    }

    @Test
    public void testObject() throws Exception {
        producer.sendUser();
        Thread.sleep(5000);
    }
}
