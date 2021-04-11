package com.example.springrabbitmq;

import java.nio.charset.StandardCharsets;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqService {

    /**
     * Rabbitmq queue 리스너 설정.
     *
     * @param message
     */
    @RabbitListener(queues = "testQueue")
    public void testQueueListener(Message message) {
        System.out.println("Received: " + message);
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
    }

    /**
     * Rabbitmq exchange topic 리스너 설정.
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue,
        exchange = @Exchange(value = "test.topic", type = "topic"),
        key = "topic.#")
    )
    public void topicBinding(Message message) {
        System.out.println("Received: " + message);
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue,
        exchange = @Exchange(value = "test.topic2", type = "topic"),
        key = "topic2.#")
    )
    public void topicBinding2(Message message) {
        System.out.println("Received: " + message);
        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
