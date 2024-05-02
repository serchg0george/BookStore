package com.bookstore.service;

import com.bookstore.dto.CustomerDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.search.CustomerSearchRequest;

import java.util.List;

public interface CustomerService extends GenericService<CustomerEntity, CustomerDto> {

    List<CustomerDto> findCustomerByCriteria(CustomerSearchRequest request);

}
