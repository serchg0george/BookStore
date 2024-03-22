package com.bookstore.mapper.base;

public interface BaseMapper<E, D> {
    D mapEntityToDto(E entity);

    E mapDtoToEntity(D dto);
}
