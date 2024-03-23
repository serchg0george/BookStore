package com.bookstore.service;

import com.bookstore.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customer);

    CustomerDto getCustomerById(Long customerId);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomer(CustomerDto customer);

    void deleteCustomer(Long customerId);
}
