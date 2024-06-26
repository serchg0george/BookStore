package com.bookstore.services;

import com.bookstore.dtos.CustomerDto;
import com.bookstore.entities.CustomerEntity;
import com.bookstore.search.CustomerSearchRequest;

import java.util.List;

public interface CustomerService extends GenericService<CustomerEntity, CustomerDto> {

    List<CustomerDto> findCustomerByCriteria(CustomerSearchRequest request);

}
