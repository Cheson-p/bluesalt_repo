package com.bluesalt.billing_service.events.producers;

import com.bluesalt.billing_service.events.TransactionDetailsEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionDetailsEventProducer {
    @Value("${application.queue}")
    private String billing_queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTransactionNotificationEvent(TransactionDetailsEvent transactionDetailsEvent) throws JsonProcessingException {
        String event = new ObjectMapper().writeValueAsString(transactionDetailsEvent);
        rabbitTemplate.convertAndSend(billing_queue, event);
        log.info(" Sent event [{}] ", event);
    }

}
