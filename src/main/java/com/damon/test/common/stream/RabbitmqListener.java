package com.damon.test.common.stream;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RabbitmqListener {

    @RabbitListener(queues = "topic.message")
    public void processMessage(Message message) {
        byte[] body = message.getBody();
        if(null != body) {
            try {
                String msg = new String(body, StandardCharsets.UTF_8 );
                System.out.println("topic.message receive message:" + msg);
            } catch(Exception e) {
                log.error("监听topic.message失败，{}", e);
            }
        }
    }

    @RabbitListener(queues = "topic.messages")
    public void processMessages(Message message) {
        byte[] body = message.getBody();
        if(null != body) {
            try {
                String msg = new String(body, StandardCharsets.UTF_8 );
                System.out.println("topic.messages receive message:" + msg);
            } catch(Exception e) {
                log.error("监听topic.messages失败，{}", e);
            }
        }
    }

}
