package com.bookstore.mappers;

import com.bookstore.dtos.OrderItemDto;
import com.bookstore.entities.OrderItemEntity;
import com.bookstore.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper extends BaseMapper<OrderItemEntity, OrderItemDto> {

    @Override
    OrderItemDto mapEntityToDto(OrderItemEntity entity);

    @Override
    OrderItemEntity mapDtoToEntity(OrderItemDto dto);
}
