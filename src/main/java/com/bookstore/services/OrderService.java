package com.bookstore.services;

import com.bookstore.dtos.OrderDto;
import com.bookstore.entities.OrderEntity;

public interface OrderService extends GenericService<OrderEntity, OrderDto> {

    OrderDto setOrderItemToOrder(Long orderItemId, Long orderId);

}
