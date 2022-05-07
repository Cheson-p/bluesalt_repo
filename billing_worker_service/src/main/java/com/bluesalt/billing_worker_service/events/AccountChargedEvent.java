package com.bluesalt.billing_worker_service.events;

import com.bluesalt.billing_worker_service.events.types.Status;
import lombok.Data;

@Data
public class AccountChargedEvent {
    private String transactionId;
    private Status status;
}
