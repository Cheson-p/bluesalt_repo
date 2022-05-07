package com.bluesalt.customer_service.service.impl;

import com.bluesalt.customer_service.data.entities.Customer;
import com.bluesalt.customer_service.data.models.FundAccountRequest;
import com.bluesalt.customer_service.data.repositories.CustomerRepository;
import com.bluesalt.customer_service.events.FundAccountEvent;
import com.bluesalt.customer_service.events.producers.FundAccountEventProducer;
import com.bluesalt.customer_service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FundAccountEventProducer fundAccountEventProducer;

    @Override
    public Customer fundAccount(FundAccountRequest fundAccountRequest) {
        Customer customer = customerRepository.findById(fundAccountRequest.getCustomerId()).orElseThrow(()->new RuntimeException(String.format("No Customer found with %s ", fundAccountRequest.getCustomerId())));
        customer.setBalance(customer.getBalance().add(fundAccountRequest.getAmount()));
        customerRepository.save(customer);
        // Build and send event
        FundAccountEvent fundAccountEvent = new FundAccountEvent();
        fundAccountEvent.setCustomerId(fundAccountRequest.getCustomerId());
        fundAccountEvent.setAmount(fundAccountRequest.getAmount());
        fundAccountEventProducer.sendFundAccountEvent(fundAccountEvent);
        return customer;
    }



}
