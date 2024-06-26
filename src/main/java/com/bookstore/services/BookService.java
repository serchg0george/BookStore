package com.bookstore.services;

import com.bookstore.dtos.BookDto;
import com.bookstore.entities.BookEntity;
import com.bookstore.search.BookSearchRequest;

import java.util.List;

public interface BookService extends GenericService<BookEntity, BookDto> {

    List<BookDto> findBookByCriteria(BookSearchRequest request);

}
