package com.bookstore.service.impl;

import com.bookstore.dto.OrderDto;
import com.bookstore.entity.OrderEntity;
import com.bookstore.entity.OrderItemEntity;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.mapper.base.BaseMapper;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl extends GenericServiceImpl<OrderEntity, OrderDto> implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public BaseMapper<OrderEntity, OrderDto> baseMapper() {
        return orderMapper;
    }

    @Override
    public JpaRepository<OrderEntity, Long> repository() {
        return orderRepository;
    }

    @Override
    public OrderDto setOrderItemToOrder(Long orderItemId, Long orderId) {
        OrderItemEntity orderItemEntity = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item isn't exist"));
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order isn't exist"));
        orderItemEntity.setOrder(orderEntity);
        orderItemRepository.save(orderItemEntity);
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        return orderMapper.mapEntityToDto(order.get());
    }

}
