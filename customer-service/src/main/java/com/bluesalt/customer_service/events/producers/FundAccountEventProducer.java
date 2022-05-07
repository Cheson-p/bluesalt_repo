package com.bluesalt.customer_service.events.producers;

import com.bluesalt.customer_service.events.FundAccountEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FundAccountEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${application.queue}")
    private String queue;

    public void sendFundAccountEvent(FundAccountEvent fundAccountEvent){
        try {
             String event = new ObjectMapper().writeValueAsString(fundAccountEvent);
             rabbitTemplate.convertAndSend(queue, event);
             log.info(" Sent event [{}] [{}]", fundAccountEvent.getClass(), event);
        } catch (JsonProcessingException e) {
            log.error(" Unable to convert data [{}], reason [{}]", fundAccountEvent, e.getMessage());
        }
    }

}
