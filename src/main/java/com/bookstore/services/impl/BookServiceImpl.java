package com.bookstore.services.impl;

import com.bookstore.dtos.BookDto;
import com.bookstore.entities.BookEntity;
import com.bookstore.mappers.BookMapper;
import com.bookstore.mappers.base.BaseMapper;
import com.bookstore.repositories.BookRepository;
import com.bookstore.search.BookSearchRequest;
import com.bookstore.services.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl extends GenericServiceImpl<BookEntity, BookDto> implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final EntityManager entityManager;

    @Override
    public BaseMapper<BookEntity, BookDto> baseMapper() {
        return bookMapper;
    }

    @Override
    public JpaRepository<BookEntity, Long> repository() {
        return bookRepository;
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
