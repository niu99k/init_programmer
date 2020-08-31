package com.gg.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class Sender {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send() {
        System.out.println(rabbitTemplate);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc", "信息描述..");
        messageProperties.getHeaders().put("type", "自定义消息类型..");
        Message message = new Message("Hello RabbitMQ2".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("testTopic", "testRoutingKey", message);
    }
}
