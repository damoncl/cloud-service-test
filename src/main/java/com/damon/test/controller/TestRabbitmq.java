package com.damon.test.controller;


import io.swagger.annotations.Api;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试rabbit")
@RestController
@RequestMapping("/rabbit")
public class TestRabbitmq {

    @Autowired
    private AmqpTemplate amqpTemplate;


    @GetMapping("/send")
    public String send() {
        send1();
        send2();
        return "success";
    }

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
    }

    public void receive1() {
        Object o = amqpTemplate.receiveAndConvert("topic.message");
        System.out.println("topic.message receive message:" + o);
    }

    public void receive2() {
        Object o = amqpTemplate.receiveAndConvert("topic.messages");
        System.out.println("topic.messages receive message:" + o);
    }
}
