package com.bookstore.service.impl;

import com.bookstore.dto.CustomerDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.mapper.CustomerMapper;
import com.bookstore.repository.CustomerRepository;
import com.bookstore.search.CustomerSearchRequest;
import com.bookstore.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
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
    @Transactional
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

    @Override
    public List<CustomerDto> findCustomerByCriteria(CustomerSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> criteriaQuery = criteriaBuilder.createQuery(CustomerEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<CustomerEntity> root = criteriaQuery.from(CustomerEntity.class);

        if (request.getName() != null && !request.getName().isBlank()) {
            Predicate namePredicate = criteriaBuilder
                    .like(root.get("customerName"), "%" + request.getName() + "%");
            predicates.add(namePredicate);
        } else if (request.getAddress() != null && !request.getAddress().isBlank()) {
            Predicate addressPredicate = criteriaBuilder
                    .like(root.get("customerAddress"), "%" + request.getAddress() + "%");
            predicates.add(addressPredicate);
        } else if (request.getEmail() != null && !request.getEmail().isBlank()) {
            Predicate emailPredicate = criteriaBuilder
                    .like(root.get("customerEmail"), "%" + request.getEmail() + "%");
            predicates.add(emailPredicate);
        } else if (request.getPhone() != null && !request.getPhone().isBlank()) {
            Predicate phonePredicate = criteriaBuilder
                    .like(root.get("customerPhoneNumber"), "%" + request.getPhone() + "%");
            predicates.add(phonePredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<CustomerEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream()
                .map(customerMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
