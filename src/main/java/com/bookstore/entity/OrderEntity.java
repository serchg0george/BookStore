package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_orders")
public class OrderEntity extends BaseEntity{

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItemEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    CustomerEntity customer;
}
