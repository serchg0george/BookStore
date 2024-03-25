package com.bookstore.service.impl;

import com.bookstore.dto.OrderItemDto;
import com.bookstore.entity.OrderItemEntity;
import com.bookstore.mapper.OrderItemMapper;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.service.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderItemDto createOrderItem(OrderItemDto orderItem) {
        OrderItemEntity orderItemEntity = orderItemMapper.mapDtoToEntity(orderItem);
        OrderItemEntity savedOrderItem = orderItemRepository.save(orderItemEntity);
        return orderItemMapper.mapEntityToDto(savedOrderItem);
    }

    @Override
    public OrderItemDto getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .map(orderItemMapper::mapEntityToDto)
                .orElseThrow(() -> new RuntimeException("Order for this item isn't exist"));
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItemEntity> orderItemEntityList = orderItemRepository.findAll();
        return orderItemEntityList.stream()
                .map(orderItemMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderItemDto updateOrderItem(OrderItemDto orderItem) {
        OrderItemEntity orderItemEntity = orderItemMapper.mapDtoToEntity(orderItem);
        orderItemRepository.findById(orderItemEntity.getId());
        Optional<OrderItemEntity> optionalOrderItemEntity = orderItemRepository.findById(orderItemEntity.getId());
        if (optionalOrderItemEntity.isEmpty()) {
            throw new RuntimeException("Order for this item isn't exist");
        }
        OrderItemEntity updatedOrderItem = optionalOrderItemEntity.get();
        updatedOrderItem.setBook(orderItemEntity.getBook());
        updatedOrderItem.setOrder(orderItemEntity.getOrder());
        updatedOrderItem.setQuantity(orderItemEntity.getQuantity());
        return orderItemMapper.mapEntityToDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        Optional<OrderItemEntity> optionalOrderItemEntity = orderItemRepository.findById(orderItemId);
        if (optionalOrderItemEntity.isEmpty()) {
            throw new RuntimeException("Order for this item isn't exist");
        }
        orderItemRepository.deleteById(orderItemId);
    }
}
