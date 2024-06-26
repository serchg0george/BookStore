package com.bookstore.controllers;

import com.bookstore.dtos.CustomerDto;
import com.bookstore.dtos.response.GenericResponse;
import com.bookstore.search.CustomerSearchRequest;
import com.bookstore.services.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookstore.controllers.ResponseStatusConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDto customer) {
        customerService.create(customer);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GenericResponse<CustomerDto>> getAllCustomers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(customerService.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long customerId) {
        return new ResponseEntity<>(customerService.getById(customerId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long customerId,
                                                 @Valid @RequestBody CustomerDto customerDto) {
        customerService.update(customerDto, customerId);
        return new ResponseEntity<>(UPDATED_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long customerId) {
        customerService.delete(customerId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDto>> searchCustomer(@RequestBody CustomerSearchRequest request) {
        List<CustomerDto> customerDtoList = customerService.findCustomerByCriteria(request);
        return ResponseEntity.ok(customerDtoList);
    }
}
