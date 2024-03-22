package com.bookstore.mapper;

import com.bookstore.dto.CustomerDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper extends BaseMapper<CustomerEntity, CustomerDto> {
    @Override
    CustomerDto mapEntityToDto(CustomerEntity entity);

    @Override
    CustomerEntity mapDtoToEntity(CustomerDto dto);

}
