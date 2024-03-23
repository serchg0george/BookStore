package com.bookstore.service;

import com.bookstore.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto order);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(OrderDto order);

    void deleteOrder(Long orderId);
}
