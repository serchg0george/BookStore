package com.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_customers")
public class CustomerEntity extends BaseEntity {

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 150, nullable = false)
    private String customerAddress;

    @Column(name = "customer_phone_number", length = 32, nullable = false)
    private String customerPhoneNumber;

    @Column(name = "customer_email", length = 40, unique = true)
    private String customerEmail;
}
