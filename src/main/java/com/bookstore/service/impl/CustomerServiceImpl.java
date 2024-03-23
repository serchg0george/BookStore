package com.bookstore.service.impl;

import com.bookstore.dto.CustomerDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.mapper.CustomerMapper;
import com.bookstore.repository.CustomerRepository;
import com.bookstore.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {
        CustomerEntity customerEntity = customerMapper.mapDtoToEntity(customer);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return customerMapper.mapEntityToDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::mapEntityToDto)
                .orElseThrow(() -> new RuntimeException("Customer isn't exist"));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return customerEntityList.stream()
                .map(customerMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer) {
        CustomerEntity customerEntity = customerMapper.mapDtoToEntity(customer);
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerEntity.getId());
        if (optionalCustomerEntity.isEmpty()) {
            throw new RuntimeException("Customer isn't exist");
        }
        CustomerEntity updatedCustomer = optionalCustomerEntity.get();
        updatedCustomer.setCustomerName(customerEntity.getCustomerName());
        updatedCustomer.setCustomerAddress(customerEntity.getCustomerAddress());
        updatedCustomer.setCustomerEmail(customerEntity.getCustomerEmail());
        updatedCustomer.setCustomerPhoneNumber(customerEntity.getCustomerPhoneNumber());
        return customerMapper.mapEntityToDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerId);
        if (optionalCustomerEntity.isEmpty()) {
            throw new RuntimeException("Customer isn't exist");
        }
        customerRepository.deleteById(customerId);
    }
}
