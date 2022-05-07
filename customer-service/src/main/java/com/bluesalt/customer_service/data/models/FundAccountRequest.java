package com.bluesalt.customer_service.data.models;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class FundAccountRequest {
 private String customerId;
 private BigDecimal amount;
}
