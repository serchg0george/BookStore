package com.bookstore.repository;

import com.bookstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByBookAuthorIgnoreCase(String author);

    List<BookEntity> findAllByBookTitleIgnoreCase(String title);

    List<BookEntity> findAllByBookIsbnIgnoreCase(String isbn);

    List<BookEntity> findAllByBookAuthorAndBookTitleIgnoreCase(String author, String title);

    List<BookEntity> findAllByBookAuthorAndBookIsbnIgnoreCase(String author, String isbn);

    List<BookEntity> findAllByBookTitleAndBookIsbnIgnoreCase(String title, String isbn);

    List<BookEntity> findAllByBookAuthorAndBookTitleAndBookIsbnIgnoreCase(String author, String title, String isbn);
}
