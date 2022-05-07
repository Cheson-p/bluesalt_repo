package com.bluesalt.billing_service.events.consumers;

import com.bluesalt.billing_service.data.entity.Billing;
import com.bluesalt.billing_service.data.entity.types.Status;
import com.bluesalt.billing_service.events.TransactionDetailsEvent;
import com.bluesalt.billing_service.service.BillingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CustomerBillingEventConsumer {

    @Autowired
    private BillingService billingService;

    @RabbitListener(queues = "${customer.queue}")
    public void createBilling(String event) throws JsonProcessingException {
        log.info(" Receive Event [{}] ", event);
        Billing billing = new ObjectMapper().readValue(event, Billing.class);
        billing.setTransactionId(UUID.randomUUID().toString());
        billing.setStatus(Status.PENDING);
        billing = billingService.saveBilling(billing);

        // Construct TransactionDetailsEvent
        TransactionDetailsEvent transactionDetailsEvent = new TransactionDetailsEvent();
        transactionDetailsEvent.setAmount(billing.getAmount());
        transactionDetailsEvent.setCustomerId(billing.getCustomerId());
        transactionDetailsEvent.setTransactionId(billing.getTransactionId());

        billingService.publishTransactionDetailsEvent(transactionDetailsEvent);
        log.info(" Sent Event [{}] to Billing Worker Service", transactionDetailsEvent);

    }

    @RabbitListener(queues = "${application.queue}")
    public void updateBilling(String event) throws JsonProcessingException {
            log.info(" Got Event [{}] ", event);
            TransactionDetailsEvent transactionDetailsEvent =  new ObjectMapper().readValue(event, TransactionDetailsEvent.class);
            billingService.updateBilling(transactionDetailsEvent.getTransactionId(), Status.SUCCESS.toString());
            log.info(" Billing updated ");
    }

}
