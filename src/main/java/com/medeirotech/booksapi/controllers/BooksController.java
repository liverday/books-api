package com.medeirotech.booksapi.controllers;

import com.medeirotech.booksapi.models.Author;
import com.medeirotech.booksapi.models.Book;
import com.medeirotech.booksapi.models.CreateBookRequest;
import com.medeirotech.booksapi.models.UpdateBookRequest;
import com.medeirotech.booksapi.repositories.AuthorRepository;
import com.medeirotech.booksapi.repositories.BooksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final List<Book> books = new ArrayList<>();
    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    public BooksController(final BooksRepository booksRepository,
                           final AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Book> getBooks() {
        return booksRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(
            @PathVariable UUID id
    ) {
        Optional<Book> existingBook = booksRepository.findById(id.toString());

        if (existingBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existingBook.get());
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody CreateBookRequest request) {
        Optional<Author> existingAuthor = authorRepository.findById(request.getAuthorId());

        if (existingAuthor.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found");
        }

        Book book = new Book(
                null,
                request.getTitle(),
                request.getDescription(),
                existingAuthor.get()
        );

        return ResponseEntity.ok(booksRepository.save(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(
            @PathVariable UUID id,
            @RequestBody UpdateBookRequest request
    ) {
        Optional<Book> existingBook = booksRepository.findById(id.toString());

        if (existingBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book bookToUpdate = existingBook.get();

        //bookToUpdate.setAuthor(request.getAuthor());
        bookToUpdate.setDescription(request.getDescription());
        bookToUpdate.setTitle(request.getTitle());

        return ResponseEntity.ok(booksRepository.save(bookToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(
            @PathVariable UUID id
    ) {
        boolean existingBook = booksRepository.existsById(id.toString());

        if (!existingBook) {
            return ResponseEntity.notFound().build();
        }

        booksRepository.deleteById(id.toString());

        return ResponseEntity.noContent().build();
    }
}
