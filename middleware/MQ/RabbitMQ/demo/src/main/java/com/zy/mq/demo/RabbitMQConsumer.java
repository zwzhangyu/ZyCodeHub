package com.zy.mq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "your_queue")
    public void receiveMessage(String message, @Header(value = "TraceID",required = false) String traceId) {
        System.out.println("Received message: " + message);
        System.out.println("Trace ID: " + traceId);
    }
}
