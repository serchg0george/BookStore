package com.bookstore.mappers;

import com.bookstore.dtos.BookDto;
import com.bookstore.entities.BookEntity;
import com.bookstore.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper extends BaseMapper<BookEntity, BookDto> {

    @Override
    BookDto mapEntityToDto(BookEntity entity);

    @Override
    BookEntity mapDtoToEntity(BookDto dto);
}
