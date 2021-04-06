package com.example.springrabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitListenerService {

    @RabbitListener(queues = "testQueue")
    public void testQueueListener(String message) {
        System.out.println(message);
    }
}
