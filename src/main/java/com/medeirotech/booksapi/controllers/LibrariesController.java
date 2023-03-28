package com.medeirotech.booksapi.controllers;

import com.medeirotech.booksapi.models.CreateLibraryRequest;
import com.medeirotech.booksapi.models.Library;
import com.medeirotech.booksapi.repositories.LibraryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibrariesController {
    private final LibraryRepository libraryRepository;

    public LibrariesController(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @GetMapping
    public List<Library> getLibraries() {
        return libraryRepository.findAll();
    }

    @PostMapping
    public Library createLibrary(
            @RequestBody CreateLibraryRequest request
    ) {
       final Library library = new Library(
               null,
               request.getName(),
               Collections.emptyList()
       );

       return libraryRepository.save(library);
    }
}
