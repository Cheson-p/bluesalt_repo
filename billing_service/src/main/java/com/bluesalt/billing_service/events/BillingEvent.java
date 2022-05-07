package com.bluesalt.billing_service.events;

import com.bluesalt.billing_service.data.entity.types.Status;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class BillingEvent {
    private String customerId;
    private Status status;
    private BigDecimal amount;
    private String transactionId;
}
