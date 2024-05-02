package com.bookstore.service;

import java.util.List;

public interface GenericService<E, D> {

    D create(D dto);

    List<D> getAll();

    D getbyId(Long id);

    D update(D dto, Long id);

    void delete(Long id);

}
