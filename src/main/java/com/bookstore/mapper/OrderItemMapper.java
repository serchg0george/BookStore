package com.bookstore.mapper;

import com.bookstore.dto.OrderItemDto;
import com.bookstore.entity.OrderItemEntity;
import com.bookstore.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper extends BaseMapper<OrderItemEntity, OrderItemDto> {

    @Override
    OrderItemDto mapEntityToDto(OrderItemEntity entity);

    @Override
    OrderItemEntity mapDtoToEntity(OrderItemDto dto);
}
