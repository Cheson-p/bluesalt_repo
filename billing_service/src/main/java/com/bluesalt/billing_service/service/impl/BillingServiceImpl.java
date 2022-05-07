package com.bluesalt.billing_service.service.impl;

import com.bluesalt.billing_service.data.entity.Billing;
import com.bluesalt.billing_service.data.entity.types.Status;
import com.bluesalt.billing_service.data.repositories.BillingRepository;
import com.bluesalt.billing_service.events.TransactionDetailsEvent;
import com.bluesalt.billing_service.events.producers.TransactionDetailsEventProducer;
import com.bluesalt.billing_service.service.BillingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceImpl implements BillingService {
    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private TransactionDetailsEventProducer transactionDetailsEventProducer;

    @Override
    public Billing saveBilling(Billing billing) {
            Billing savedBilling =  billingRepository.save(billing);
            return savedBilling;
    }

    @Override
    public void publishTransactionDetailsEvent(TransactionDetailsEvent transactionDetailsEvent) {
        try {
            transactionDetailsEventProducer.sendTransactionNotificationEvent(transactionDetailsEvent);
        } catch (JsonProcessingException e) {
           log.error(" Error occurred [{}] ", e.getMessage());
        }
    }

    @Override
    public Billing updateBilling(String  transactionId, String status) {
        Billing billingDT0 = getBillingByTransactionId(transactionId);
        billingDT0.setStatus(Status.valueOf(status));
        billingDT0 = billingRepository.save(billingDT0);
        return billingDT0;
    }

    @Override
    public Billing getBillingByTransactionId(String transactionId) {
        return billingRepository.findByTransactionId(transactionId);
    }

}
