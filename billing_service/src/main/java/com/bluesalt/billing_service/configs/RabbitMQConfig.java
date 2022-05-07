package com.bluesalt.billing_service.configs;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Minimal Configuration

    @Value("${application.queue}")
    private String billingQueue;
    @Value("${billing_worker.queue}")
    private String billingWorkerQueue;
    @Value("${customer.queue}")
    private String customerQueue;
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
        rabbitAdmin.declareQueue(new Queue(billingQueue));
        rabbitAdmin.declareQueue(new Queue(billingWorkerQueue));
        rabbitAdmin.declareQueue(new Queue(customerQueue));
        return rabbitAdmin;
    }


}
