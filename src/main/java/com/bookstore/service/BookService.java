package com.bookstore.service;

import com.bookstore.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto book);

    BookDto getBookById(Long bookId);

    List<BookDto> getAllBooks();

    BookDto updateBook(BookDto book);

    void deleteBook(Long bookId);

    List<BookDto> findBookByAuthor(String search);

    List<BookDto> findBookByTitle(String title);

    List<BookDto> findBookByIsbn(String isbn);

    List<BookDto> findBookByAuthorAndTitle(String author, String title);

    List<BookDto> findBookByAuthorAndIsbn(String author, String isbn);

    List<BookDto> findBookByTitleAndIsbn(String title, String isbn);

    List<BookDto> findBookByAuthorAndTitleAndIsbn(String author, String title, String isbn);
}
