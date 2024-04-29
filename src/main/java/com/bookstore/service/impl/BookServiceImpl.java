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

    @Override
    public List<BookDto> findBookByAuthor(String author) {
        List<BookEntity> bookEntityList = bookRepository.findAllByBookAuthorIgnoreCase(author);
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBookByTitle(String title) {
        List<BookEntity> bookEntityList = bookRepository.findAllByBookTitleIgnoreCase(title);
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBookByIsbn(String isbn) {
        List<BookEntity> bookEntityList = bookRepository.findAllByBookIsbnIgnoreCase(isbn);
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBookByAuthorAndTitle(String author, String title) {
        List<BookEntity> bookEntityList = bookRepository.findAllByBookAuthorAndBookTitleIgnoreCase(author, title);
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBookByAuthorAndIsbn(String author, String isbn) {
        List<BookEntity> bookEntityList = bookRepository.findAllByBookAuthorAndBookIsbnIgnoreCase(author, isbn);
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBookByTitleAndIsbn(String title, String isbn) {
        List<BookEntity> bookEntityList = bookRepository.findAllByBookTitleAndBookIsbnIgnoreCase(title, isbn);
        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findBookByAuthorAndTitleAndIsbn(String author, String title, String isbn) {

        List<BookEntity> bookEntityList = bookRepository
                .findAllByBookAuthorAndBookTitleAndBookIsbnIgnoreCase(author, title, isbn);

        return bookEntityList.stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
