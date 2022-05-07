package com.bluesalt.billing_service.service;

import com.bluesalt.billing_service.data.entity.Billing;
import com.bluesalt.billing_service.events.TransactionDetailsEvent;

public interface BillingService {

    Billing saveBilling(Billing billing);
    void publishTransactionDetailsEvent(TransactionDetailsEvent transactionDetailsEvent);
    Billing updateBilling( String transactionId, String status);
    Billing getBillingByTransactionId(String transactionId);

}
