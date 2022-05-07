package com.bluesalt.billing_service.data.repositories;

import com.bluesalt.billing_service.data.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, String> {
    Billing findByTransactionId(String transactionId);
}
