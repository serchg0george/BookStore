package com.bookstore.services.impl;

import com.bookstore.dtos.CustomerDto;
import com.bookstore.entities.CustomerEntity;
import com.bookstore.mappers.CustomerMapper;
import com.bookstore.mappers.base.BaseMapper;
import com.bookstore.repositories.CustomerRepository;
import com.bookstore.search.CustomerSearchRequest;
import com.bookstore.services.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl extends GenericServiceImpl<CustomerEntity, CustomerDto> implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<CustomerEntity, CustomerDto> baseMapper() {
        return customerMapper;
    }

    @Override
    public JpaRepository<CustomerEntity, Long> repository() {
        return customerRepository;
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
