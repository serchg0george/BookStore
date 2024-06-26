package com.bookstore.mappers;

import com.bookstore.dtos.OrderDto;
import com.bookstore.entities.OrderEntity;
import com.bookstore.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper extends BaseMapper<OrderEntity, OrderDto> {

    @Override
    OrderDto mapEntityToDto(OrderEntity entity);

    @Override
    OrderEntity mapDtoToEntity(OrderDto dto);
}
