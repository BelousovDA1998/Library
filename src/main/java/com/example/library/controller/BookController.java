package com.example.library.controller;

import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.BookUnavailableException;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/{id}/issue")
    public ResponseEntity<?> issueBook(@PathVariable Long id) {
        try {
            Book book = bookService.issueBook(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException | BookUnavailableException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        try {
            Book book = bookService.returnBook(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}