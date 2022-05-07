package com.bluesalt.customer_service;

import com.bluesalt.customer_service.data.entities.Customer;
import com.bluesalt.customer_service.data.models.FundAccountRequest;
import com.bluesalt.customer_service.data.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceApplicationTests {
    @Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void testFundAccount() throws Exception {

		Customer customer = new Customer();
		customer.setBalance(new BigDecimal("2000.00"));
		customer.setFirstname("Test");
		customer.setLastname("TestPassword");
		customer = customerRepository.save(customer);

		FundAccountRequest fundAccountRequest = new FundAccountRequest();
		fundAccountRequest.setCustomerId(customer.getId());
		fundAccountRequest.setAmount(new BigDecimal("500.00"));

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/account/fund")
						.content(new ObjectMapper().writeValueAsString(fundAccountRequest))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		Customer customerEntity = customerRepository.findById(customer.getId()).get();
		assertThat(customerEntity.getBalance(), Matchers.equalTo(customer.getBalance().add(fundAccountRequest.getAmount())));

	}

}
