package com.bookstore.service.impl;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.BookEntity;
import com.bookstore.mapper.BookMapper;
import com.bookstore.repository.BookRepository;
import com.bookstore.search.BookSearchRequest;
import com.bookstore.service.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final EntityManager entityManager;

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
    public List<BookDto> findBookByCriteria(BookSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<BookEntity> root = criteriaQuery.from(BookEntity.class);

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            Predicate titlePredicate = criteriaBuilder
                    .like(root.get("bookTitle"), "%" + request.getTitle() + "%");
            predicates.add(titlePredicate);
        } else if (request.getAuthor() != null && !request.getAuthor().isBlank()) {
            Predicate authorPredicate = criteriaBuilder
                    .like(root.get("bookAuthor"), "%" + request.getAuthor() + "%");
            predicates.add(authorPredicate);
        } else if (request.getIsbn() != null && !request.getIsbn().isBlank()) {
            Predicate isbnPredicate = criteriaBuilder
                    .like(root.get("bookIsbn"), "%" + request.getIsbn() + "%");
            predicates.add(isbnPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<BookEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().stream()
                .map(bookMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
