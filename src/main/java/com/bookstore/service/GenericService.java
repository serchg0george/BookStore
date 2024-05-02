package com.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<E, D> {

    D create(D dto);

    Page<D> getAll(Pageable pageable);

    D getbyId(Long id);

    D update(D dto, Long id);

    void delete(Long id);

}
