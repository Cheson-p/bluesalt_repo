package com.bluesalt.customer_service.service;

import com.bluesalt.customer_service.data.entities.Customer;
import com.bluesalt.customer_service.data.models.FundAccountRequest;

public interface CustomerService {
    Customer fundAccount(FundAccountRequest fundAccountRequest);
}
