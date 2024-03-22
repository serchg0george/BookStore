package com.bookstore.mapper;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.BookEntity;
import com.bookstore.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper extends BaseMapper<BookEntity, BookDto> {

    @Override
    BookDto mapEntityToDto(BookEntity entity);

    @Override
    BookEntity mapDtoToEntity(BookDto dto);
}
