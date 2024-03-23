package com.bookstore.service;

import com.bookstore.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItem);

    OrderItemDto getOrderItemById(Long orderItemId);

    List<OrderItemDto> getAllOrderItems();

    OrderItemDto updateOrderItem(OrderItemDto orderItem);

    void deleteOrderItem(Long orderItemId);
}
