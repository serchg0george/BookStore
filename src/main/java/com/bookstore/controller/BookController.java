package com.bookstore.controller;

import com.bookstore.dto.BookDto;
import com.bookstore.mapper.BookMapper;
import com.bookstore.search.BookSearchRequest;
import com.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookstore.controller.ResponseStatusConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody BookDto book) {
        bookService.create(book);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pageable) {
        return new ResponseEntity<>(bookService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
        return new ResponseEntity<>(bookService.getbyId(bookId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long bookId,
                                             @Valid @RequestBody BookDto bookDto) {
        bookService.update(bookDto, bookId);
        return new ResponseEntity<>(UPDATED_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long bookId) {
        bookService.delete(bookId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> findBookByCriteria(@RequestBody BookSearchRequest request) {
        List<BookDto> bookDtoList = bookService.findBookByCriteria(request);
        return ResponseEntity.ok(bookDtoList);
    }
}
