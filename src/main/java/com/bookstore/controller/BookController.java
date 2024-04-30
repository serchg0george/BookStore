package com.bookstore.controller;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.BookEntity;
import com.bookstore.mapper.BookMapper;
import com.bookstore.search.BookSearchRequest;
import com.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookstore.controller.ResponseStatusConstants.CREATED_SUCCESS;
import static com.bookstore.controller.ResponseStatusConstants.DELETED_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody BookDto book) {
        bookService.createBook(book);
        return new ResponseEntity<>(CREATED_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long bookId,
                                              @Valid @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapDtoToEntity(bookDto);
        bookEntity.setId(bookId);
        BookDto updatedBook = bookService.updateBook(bookMapper.mapEntityToDto(bookEntity));
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(DELETED_SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> findBookByCriteria(@RequestBody BookSearchRequest request) {
        List<BookDto> bookDtoList = bookService.findBookByCriteria(request);
        return ResponseEntity.ok(bookDtoList);
    }
}
