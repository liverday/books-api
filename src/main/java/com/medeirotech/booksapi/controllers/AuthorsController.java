package com.medeirotech.booksapi.controllers;

import com.medeirotech.booksapi.models.Author;
import com.medeirotech.booksapi.models.CreateAuthorRequest;
import com.medeirotech.booksapi.repositories.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorRepository authorRepository;

    public AuthorsController(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author createBook(
            @RequestBody CreateAuthorRequest request
    ) {
        Author author = new Author(
                null,
                request.getName(),
                Collections.emptyList()
        );

        return authorRepository.save(author);
    }
}
