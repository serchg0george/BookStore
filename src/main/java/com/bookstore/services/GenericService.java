package com.bookstore.services;

import com.bookstore.dtos.response.GenericResponse;

public interface GenericService<E, D> {

    D create(D dto);

    GenericResponse<D> getAll(int pageNo, int pageSize);

    D getById(Long id);

    D update(D dto, Long id);

    void delete(Long id);

}
