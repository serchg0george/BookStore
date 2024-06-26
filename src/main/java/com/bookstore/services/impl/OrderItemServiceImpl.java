package com.bookstore.services.impl;

import com.bookstore.dtos.OrderItemDto;
import com.bookstore.entities.OrderItemEntity;
import com.bookstore.mappers.OrderItemMapper;
import com.bookstore.mappers.base.BaseMapper;
import com.bookstore.repositories.OrderItemRepository;
import com.bookstore.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl extends GenericServiceImpl<OrderItemEntity, OrderItemDto> implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public BaseMapper<OrderItemEntity, OrderItemDto> baseMapper() {
        return orderItemMapper;
    }

    @Override
    public JpaRepository<OrderItemEntity, Long> repository() {
        return orderItemRepository;
    }

}
