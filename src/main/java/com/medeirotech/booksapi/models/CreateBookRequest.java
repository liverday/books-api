package com.medeirotech.booksapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String description;
    private String author;
}
