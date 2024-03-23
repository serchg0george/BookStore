package com.bookstore.service;

import com.bookstore.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto book);

    BookDto getBookById(Long bookId);

    List<BookDto> getAllBooks();

    BookDto updateBook(BookDto book);

    void deleteBook(Long bookId);
}
