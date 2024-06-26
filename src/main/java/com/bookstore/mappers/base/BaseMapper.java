package com.bookstore.mappers.base;

public interface BaseMapper<E, D> {
    D mapEntityToDto(E entity);

    E mapDtoToEntity(D dto);
}
