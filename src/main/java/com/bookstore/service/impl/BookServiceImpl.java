package com.bookstore.service.impl;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.BookEntity;
import com.bookstore.mapper.BookMapper;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookDto createBook(BookDto book) {
        BookEntity bookEntity = bookMapper.mapDtoToEntity(book);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return bookMapper.mapEntityToDto(savedBook);
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::mapEntityToDto)
                .orElseThrow(() -> new RuntimeException("Book isn't exist"));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDto updateBook(BookDto book) {
        BookEntity bookEntity = bookMapper.mapDtoToEntity(book);
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookEntity.getId());
        if (optionalBookEntity.isEmpty()) {
            throw new RuntimeException("Book isn't exist");
        }
        BookEntity updatedBook = optionalBookEntity.get();
        updatedBook.setBookTitle(bookEntity.getBookTitle());
        updatedBook.setBookAuthor(bookEntity.getBookAuthor());
        updatedBook.setBookIsbn(bookEntity.getBookIsbn());
        return bookMapper.mapEntityToDto(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
        if (optionalBookEntity.isEmpty()) {
            throw new RuntimeException("Book isn't exist");
        }
        bookRepository.deleteById(bookId);
    }
}
