package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.BookEntity;
import com.bookstore.search.BookSearchRequest;

import java.util.List;

public interface BookService extends GenericService<BookEntity, BookDto> {

    List<BookDto> findBookByCriteria(BookSearchRequest request);

}
