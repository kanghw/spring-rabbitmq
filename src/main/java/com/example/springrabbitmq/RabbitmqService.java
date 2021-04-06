package com.example.springrabbitmq;

import java.nio.charset.StandardCharsets;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqService {

    @RabbitListener(queues = "testQueue")
    public void testQueueListener(Message message) {
        System.out.println("Received: " + message);
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
