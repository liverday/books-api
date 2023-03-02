package com.medeirotech.booksapi.repositories;

import com.medeirotech.booksapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, String> {
}
