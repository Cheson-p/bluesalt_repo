package com.bluesalt.customer_service.data.repositories;

import com.bluesalt.customer_service.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
