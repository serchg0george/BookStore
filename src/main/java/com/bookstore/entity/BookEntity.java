package com.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_books")
public class BookEntity extends BaseEntity{

    @Column(name = "book_title", length = 60, nullable = false, unique = true)
    private String bookTitle;

    @Column(name = "book_author", length = 100, nullable = false)
    private String bookAuthor;

    @Column(name = "book_isbn", length = 13, nullable = false)
    private int bookIsbn;

}
