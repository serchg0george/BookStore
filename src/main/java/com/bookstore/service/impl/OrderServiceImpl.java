package com.bookstore.service.impl;

import com.bookstore.dto.OrderDto;
import com.bookstore.entity.OrderEntity;
import com.bookstore.mapper.OrderMapper;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = orderMapper.mapDtoToEntity(order);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderMapper.mapEntityToDto(savedOrderEntity);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::mapEntityToDto)
                .orElseThrow(() -> new RuntimeException("Order isn't exist"));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto updateOrder(OrderDto order) {
        OrderEntity orderEntity = orderMapper.mapDtoToEntity(order);
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderEntity.getId());
        if (optionalOrderEntity.isEmpty()) {
            throw new RuntimeException("Order isn't exist");
        }
        OrderEntity updatedOrder = optionalOrderEntity.get();
        updatedOrder.setOrderItemEntity(orderEntity.getOrderItemEntity());
        updatedOrder.setCustomer(orderEntity.getCustomer());
        return orderMapper.mapEntityToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);
        if (optionalOrderEntity.isEmpty()) {
            throw new RuntimeException("Order isn't exist");
        }
        orderRepository.deleteById(orderId);
    }
}
