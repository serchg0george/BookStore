package com.bookstore.search;

import lombok.Getter;

@Getter
public class BookSearchRequest {
    private String author;
    private String title;
    private String isbn;
}
