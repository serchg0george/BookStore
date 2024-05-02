package com.bookstore.service;

import com.bookstore.dto.OrderDto;
import com.bookstore.entity.OrderEntity;

public interface OrderService extends GenericService<OrderEntity, OrderDto> {

    OrderDto setOrderItemToOrder(Long orderItemId, Long orderId);

}
