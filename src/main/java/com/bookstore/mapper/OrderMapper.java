package com.bookstore.mapper;

import com.bookstore.dto.OrderDto;
import com.bookstore.entity.OrderEntity;
import com.bookstore.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper extends BaseMapper<OrderEntity, OrderDto> {

    @Override
    OrderDto mapEntityToDto(OrderEntity entity);

    @Override
    OrderEntity mapDtoToEntity(OrderDto dto);
}
