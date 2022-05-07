package com.bluesalt.customer_service.controller;

import com.bluesalt.customer_service.data.entities.Customer;
import com.bluesalt.customer_service.data.models.FundAccountRequest;
import com.bluesalt.customer_service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/account/fund", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> fundAccount(@RequestBody FundAccountRequest fundAccountRequest) {
        log.info(" Initiating Fund Account Operation [{}]",fundAccountRequest );
        Customer customer = customerService.fundAccount(fundAccountRequest);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
