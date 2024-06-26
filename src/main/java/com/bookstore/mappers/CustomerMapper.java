package com.bookstore.mappers;

import com.bookstore.dtos.CustomerDto;
import com.bookstore.entities.CustomerEntity;
import com.bookstore.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper extends BaseMapper<CustomerEntity, CustomerDto> {
    @Override
    CustomerDto mapEntityToDto(CustomerEntity entity);

    @Override
    CustomerEntity mapDtoToEntity(CustomerDto dto);

}
