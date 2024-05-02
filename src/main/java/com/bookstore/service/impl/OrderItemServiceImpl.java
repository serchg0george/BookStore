package com.bookstore.service.impl;

import com.bookstore.dto.OrderItemDto;
import com.bookstore.entity.OrderItemEntity;
import com.bookstore.mapper.OrderItemMapper;
import com.bookstore.mapper.base.BaseMapper;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.service.OrderItemService;
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
