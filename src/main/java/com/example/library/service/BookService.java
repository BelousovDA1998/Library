package com.example.library.service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.BookUnavailableException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book issueBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        if ("available".equals(book.getStatus())) {
            book.setStatus("issued");
            return bookRepository.save(book);
        } else {
            throw new BookUnavailableException("Book is not available");
        }
    }

    @Transactional
    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        if ("issued".equals(book.getStatus())) {
            book.setStatus("available");
            return bookRepository.save(book);
        } else {
            throw new IllegalStateException("Book is not issued");
        }
    }

    @PostConstruct
    public void initBooks() {
        bookRepository.save(new Book(null,"Название книги 1", "Автор 1", new Date(),"available"));
        bookRepository.save(new Book(null,"Название книги 2", "Автор 2", new Date(),"available"));
    }

}