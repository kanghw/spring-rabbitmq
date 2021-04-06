package com.example.springrabbitmq.config;

import java.nio.charset.StandardCharsets;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory());
        container.setQueueNames("testQueue");
        container.setMessageListener(exampleListener());
        return container;
    }

    @Bean
    public CachingConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory =
            new CachingConnectionFactory("192.168.1.219");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public MessageListener exampleListener() {
        return message -> {
            System.out.println("received: " + message);
            System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
        };
    }
}
