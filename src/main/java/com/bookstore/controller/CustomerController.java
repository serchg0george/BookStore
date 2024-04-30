package com.bookstore.controller;

import com.bookstore.dto.CustomerDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.mapper.CustomerMapper;
import com.bookstore.search.CustomerSearchRequest;
import com.bookstore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookstore.controller.ResponseStatusConstants.CREATED_SUCCESS;
import static com.bookstore.controller.ResponseStatusConstants.DELETED_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long customerId,
                                                      @Valid @RequestBody CustomerDto customerDto) {
        CustomerEntity customerEntity = customerMapper.mapDtoToEntity(customerDto);
        customerEntity.setId(customerId);
        CustomerDto updatedCustomer = customerService.updateCustomer(customerMapper.mapEntityToDto(customerEntity));
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDto>> searchCustomer(@RequestBody CustomerSearchRequest request) {
        List<CustomerDto> customerDtoList = customerService.findCustomerByCriteria(request);
        return ResponseEntity.ok(customerDtoList);
    }
}
