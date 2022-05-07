package com.bluesalt.billing_worker_service.events;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@ToString
public class TransactionDetailsEvent {
    private String customerId;
    private String status;
    private BigDecimal amount;
    private String transactionId;
}
