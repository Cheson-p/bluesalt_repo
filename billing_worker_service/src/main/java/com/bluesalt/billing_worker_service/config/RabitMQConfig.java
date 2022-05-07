package com.bluesalt.billing_worker_service.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQConfig {
    @Value("${application.queue}")
    private String applicationQueue;
    @Value("${billing.queue}")
    private String billingQueue;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(cachingConnectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory());
        rabbitAdmin.declareQueue(new Queue(applicationQueue));
        rabbitAdmin.declareQueue(new Queue(billingQueue));
        return rabbitAdmin;
    }

}
