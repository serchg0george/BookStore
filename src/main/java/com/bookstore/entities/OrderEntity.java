package com.bookstore.entities;

import com.bookstore.entities.base.BaseEntity;
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
public class OrderEntity extends BaseEntity {

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REMOVE}, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItemEntity;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    CustomerEntity customer;
}
