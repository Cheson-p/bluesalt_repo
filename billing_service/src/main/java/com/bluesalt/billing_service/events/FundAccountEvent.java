package com.bluesalt.billing_service.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundAccountEvent {
    private String customerId;
    private BigDecimal amount;
}
