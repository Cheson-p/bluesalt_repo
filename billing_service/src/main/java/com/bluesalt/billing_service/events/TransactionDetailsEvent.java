package com.bluesalt.billing_service.events;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionDetailsEvent {
    private String customerId;
    private String status;
    private BigDecimal amount;
    private String transactionId;
}
