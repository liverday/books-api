package com.medeirotech.booksapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private UUID id;
    private String title;
    private String description;
    private String author;
}
