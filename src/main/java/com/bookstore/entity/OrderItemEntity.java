package com.bookstore.entity;

import com.bookstore.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order_item")
public class OrderItemEntity extends BaseEntity {

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH})
    OrderEntity order;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    BookEntity book;

    @Column(name = "order_item_quantity", nullable = false)
    private Integer quantity;
}
