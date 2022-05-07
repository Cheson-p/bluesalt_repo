package com.bluesalt.billing_worker_service.service;

import com.bluesalt.billing_worker_service.events.TransactionDetailsEvent;

public interface ChargeService {
    void chargeAccount(TransactionDetailsEvent transactionDetailsEvent);
    void publishAccountChargedEvent(TransactionDetailsEvent transactionDetailsEvent);
}
