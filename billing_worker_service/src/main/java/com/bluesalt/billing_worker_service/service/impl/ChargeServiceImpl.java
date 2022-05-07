package com.bluesalt.billing_worker_service.service.impl;

import com.bluesalt.billing_worker_service.events.TransactionDetailsEvent;
import com.bluesalt.billing_worker_service.events.producers.TransactionDetailEventProducer;
import com.bluesalt.billing_worker_service.events.types.Status;
import com.bluesalt.billing_worker_service.service.ChargeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChargeServiceImpl implements ChargeService {
    @Autowired
    private TransactionDetailEventProducer transactionDetailEventProducer;
    @Override
    public void chargeAccount(TransactionDetailsEvent transactionDetailsEvent) {
        try {
            // Dummy charge account, wait for 100ms, update status to SUCCESS and proceed
            Thread.sleep(1000);
            transactionDetailsEvent.setStatus(Status.SUCCESS.toString());
            publishAccountChargedEvent(transactionDetailsEvent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publishAccountChargedEvent(TransactionDetailsEvent transactionDetailsEvent) {
        try {
            transactionDetailEventProducer.publishMessage(transactionDetailsEvent);
        } catch (JsonProcessingException e) {
           log.error(" Error occurred [{}]", e.getMessage());
        }
    }
}
