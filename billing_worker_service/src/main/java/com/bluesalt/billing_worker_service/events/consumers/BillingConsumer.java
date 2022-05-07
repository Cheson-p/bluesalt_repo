package com.bluesalt.billing_worker_service.events.consumers;

import com.bluesalt.billing_worker_service.events.TransactionDetailsEvent;
import com.bluesalt.billing_worker_service.service.ChargeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BillingConsumer {

    @Autowired
    private ChargeService chargeService;

    @RabbitListener(queues = "${billing.queue}")
    public void onRecieveBillingQueueMessage(String event) throws JsonProcessingException {
        log.info(" Got event  [{}] ", event);
        TransactionDetailsEvent transactionDetailsEvent = new ObjectMapper().readValue(event, TransactionDetailsEvent.class);
        chargeService.chargeAccount(transactionDetailsEvent);
    }


}
