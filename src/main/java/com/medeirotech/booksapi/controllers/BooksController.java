package com.medeirotech.booksapi.controllers;

import com.medeirotech.booksapi.models.Book;
import com.medeirotech.booksapi.models.CreateBookRequest;
import com.medeirotech.booksapi.models.UpdateBookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(
            @PathVariable UUID id
    ) {
        Optional<Book> existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        if (existingBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existingBook.get());
    }

    @PostMapping
    public Book createBook(@RequestBody CreateBookRequest request) {
        Book book = new Book(
                UUID.randomUUID(),
                request.getTitle(),
                request.getDescription(),
                request.getAuthor()
        );

        books.add(book);

        return book;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(
            @PathVariable UUID id,
            @RequestBody UpdateBookRequest request
    ) {
        Optional<Book> existingBook = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();

        if (existingBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book bookToUpdate = existingBook.get();

        bookToUpdate.setAuthor(request.getAuthor());
        bookToUpdate.setDescription(request.getDescription());
        bookToUpdate.setTitle(request.getTitle());

        return ResponseEntity.ok(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(
            @PathVariable UUID id
    ) {
        boolean wasDeleted = books.removeIf(book -> book.getId().equals(id));

        if (wasDeleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
