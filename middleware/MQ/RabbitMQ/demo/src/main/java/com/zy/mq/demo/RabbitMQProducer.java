package com.zy.mq.demo;

import cn.hutool.core.lang.UUID;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend("your_exchange","your_routing_key", message, m -> {
            m.getMessageProperties().getHeaders().put("TraceID", UUID.fastUUID());
            return m;
        });
    }
}

